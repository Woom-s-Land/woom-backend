package com.ee06.wooms.global.jwt.filter;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.oauth.CustomOAuth2User;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.global.jwt.JWTUtil;
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
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final String IS_COME;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (request.getRequestURI().matches("^\\/api\\/auth\\/users$")) {
            chain.doFilter(request, response);
            return;
        }

        String token = Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> Objects.equals("Authorization", cookie.getName()))
                        .map(Cookie::getValue)
                        .findAny())
                .orElse(null);

        if (token != null && jwtUtil.validateToken(token)) {
            Authentication authToken = null;
            User user = User.builder()
                    .uuid(UUID.fromString(jwtUtil.getUuid(token)))
                    .nickname(jwtUtil.getNickname(token))
                    .costume(Integer.parseInt(jwtUtil.getCostume(token)))
                    .build();

            if(Objects.equals(IS_COME, "OAUTH")) {
                CustomOAuth2User customOAuth2User = new CustomOAuth2User(user, Map.of());
                authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
            }
            if(Objects.equals(IS_COME, "COMMON")) {
                CustomUserDetails customUserDetails = new CustomUserDetails(user);
                authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            }

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        chain.doFilter(request, response);
    }
}
