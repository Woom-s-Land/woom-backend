package com.ee06.wooms.global.jwt.filter;

import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.util.CookieUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ChannelJoinFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(!uri.matches("/api/wooms/channel")) {
            chain.doFilter(request, response);
            return;
        }

        String channelUuid = request.getParameter("channelUuid");
        String token = CookieUtils.getCookie(request, "Authorization");

        String costume = jwtUtil.getCostume(token);
        String nickname = jwtUtil.getNickname(token);
        String uuid = jwtUtil.getUuid(token);

        String newAccessToken = jwtUtil.generateAccessToken(uuid, nickname, costume, channelUuid);

        CookieUtils.addCookie(response, "Authorization", newAccessToken, 216000);
        response.setStatus(HttpStatus.OK.value());

        chain.doFilter(request, response);
    }
}