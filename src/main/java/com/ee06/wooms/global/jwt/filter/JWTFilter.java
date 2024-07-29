package com.ee06.wooms.global.jwt.filter;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.exception.CustomAuthenticationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final AuthenticationEntryPoint authenticationEntryPoint;

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

        if (token == null) chain.doFilter(request, response);
        if (isNotValidate(request, response, chain, token)) return;

        CustomUserDetails customUserDetails = new CustomUserDetails(generateForAuthUser(token), Map.of());
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        chain.doFilter(request, response);
    }

    private User generateForAuthUser(String token) {
        return User.builder()
                .uuid(UUID.fromString(jwtUtil.getUuid(token)))
                .nickname(jwtUtil.getNickname(token))
                .costume(Integer.parseInt(jwtUtil.getCostume(token)))
                .build();
    }

    private boolean isNotValidate(HttpServletRequest request, HttpServletResponse response, FilterChain chain, String token) throws IOException, ServletException {
        try {
            jwtUtil.isExpired(token);
            jwtUtil.validateToken(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            authenticationEntryPoint.commence(request, response, new CustomAuthenticationException(ErrorCode.MAL_FORMED_TOKEN) {});
            return true;
        } catch (ExpiredJwtException e) {
            authenticationEntryPoint.commence(request, response, new CustomAuthenticationException(ErrorCode.EXPIRED_TOKEN) {});
            return true;
        } catch (IllegalArgumentException e) {
            authenticationEntryPoint.commence(request, response, new CustomAuthenticationException(ErrorCode.INVALID_TOKEN) {});
            return true;
        }
        return false;
    }

}
