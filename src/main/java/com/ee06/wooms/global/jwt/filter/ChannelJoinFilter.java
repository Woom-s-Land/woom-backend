package com.ee06.wooms.global.jwt.filter;

import com.ee06.wooms.global.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

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
        String token = Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> Objects.equals("Authorization", cookie.getName()))
                        .map(Cookie::getValue)
                        .findAny())
                .orElse(null);

        String costume = jwtUtil.getCostume(token);
        String nickname = jwtUtil.getNickname(token);
        String uuid = jwtUtil.getUuid(token);

        String newAccessToken = jwtUtil.generateAccessToken(uuid, nickname, costume, channelUuid);

        response.addCookie(createCookie("Authorization", newAccessToken));
        response.setStatus(HttpStatus.OK.value());

        chain.doFilter(request, response);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}