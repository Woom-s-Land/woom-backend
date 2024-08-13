package com.ee06.wooms.domain.chat.common;

import com.ee06.wooms.domain.chat.ChannelRepository;
import com.ee06.wooms.domain.chat.SessionRepository;
import com.ee06.wooms.domain.chat.entity.Channel;
import com.ee06.wooms.domain.chat.entity.Woom;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.util.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Component
public class CustomHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private final JWTUtil jwtUtil;

    private final ChannelRepository channelRepository;
    private final SessionRepository sessionRepository;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

            String token = extractTokenFromCookies(servletRequest);
            if (token != null && jwtUtil.validateToken(token)) {

                // HttpSessionHandshakeInterceptor 에서 session attribute 에 값을 넣으면 WebSocket 세션으로 전달된다고 합니다. <- 불확실
                // 참고 : https://javakyu4030.tistory.com/entry/SpringFrameworkWebSocketSession%EC%97%90%EC%84%9C-HttpSession-%EA%B0%92-%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0
                ((ServletServerHttpRequest) request).getServletRequest().getSession().setAttribute("token", token);
                String sessionId = ((ServletServerHttpRequest) request).getServletRequest().getSession().getId();


                log.info("토큰 저장 성공");
                return super.beforeHandshake(request, response, wsHandler, attributes);
                //return true;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // wsHandler.afterConnectionEstablished();

        // Channel 에 사용자 저장하기
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String sessionId = servletRequest.getSession().getId();
        String token = (String) servletRequest.getSession().getAttribute("token");
        log.info("토큰 읽기 성공");

        log.info("token : {}", token);
        UUID userUuid = UUID.fromString(jwtUtil.getUuid(token));

        log.info("userUuid : {}", userUuid);
        String nickname = jwtUtil.getNickname(token);

        log.info("nickname : {}", nickname);
        Integer costume = Integer.valueOf(jwtUtil.getCostume(token));

        log.info("costume : {}", costume);
        UUID channelUuid = UUID.fromString(jwtUtil.getChannelUuid(token));

        log.info("channelUuid : {}", channelUuid);

        log.info("userUuid : {}", userUuid);
        log.info("channelUuid : {}", channelUuid);
        Woom woom = Woom.DefaultWoom();
        woom.setWoomsId(channelUuid);
        woom.setCostume(costume);
        woom.setNickname(nickname);
        Channel channel = channelRepository.get(channelUuid);
        channel.addWoom(woom);
        log.info("addWoom 성공");
        sessionRepository.put(sessionId, new Woom(nickname, costume, channelUuid));
        channelRepository.put(channelUuid, channel); // put 해주면 woom만 추가되는건지, channel 자체가 들어가는 건지 몰라서 일단 이렇게 했습니다!
        log.info("채널 반영");
    }

    private String extractTokenFromCookies(HttpServletRequest request) {
        return CookieUtils.getCookie(request, "Authorization");
    }
}
