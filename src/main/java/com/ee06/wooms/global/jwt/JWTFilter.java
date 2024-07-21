package com.ee06.wooms.global.jwt;

import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.entity.dto.CustomOAuth2User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        String token = Arrays.stream(cookies)
                .filter((cookie) -> Objects.equals("Authorization", cookie.getName()))
                .map(Cookie::getValue)
                .findAny()
                .orElse(null);

        if(token == null) {
            log.info("존재하지 않는 토큰입니다.");
            filterChain.doFilter(request, response);
            return;
        }

        if(jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        User user = User.builder()
                .userUuid(jwtUtil.getUserUuid(token))
                .userName(jwtUtil.getUserName(token))
                .build();

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(user, Map.of());
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
