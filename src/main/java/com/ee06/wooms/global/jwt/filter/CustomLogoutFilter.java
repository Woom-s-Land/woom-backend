package com.ee06.wooms.global.jwt.filter;

import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.exception.InvalidRefreshTokenException;
import com.ee06.wooms.global.jwt.exception.InvalidTokenException;
import com.ee06.wooms.global.jwt.repository.RefreshTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {
    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //path and method verify
        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();
        if (!requestUri.matches("^\\/api/auth$") || !Objects.equals(requestMethod, "DELETE")) {
            chain.doFilter(request, response);
            return;
        }

        //get refresh token
        String refresh = Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> Objects.equals("refresh", cookie.getName()))
                        .map(Cookie::getValue)
                        .findAny())
                .orElse(null);

        //refresh null check
        if (refresh == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if(!jwtUtil.validateToken(refresh) || !Objects.equals(jwtUtil.getSubject(refresh), "refresh-token")){
            throw new InvalidTokenException();
        }

        if (refreshTokenRepository.findById(refresh).isEmpty()) {
            throw new InvalidRefreshTokenException();
        }

        //로그아웃 진행
        //Refresh 토큰 DB에서 제거
        refreshTokenRepository.deleteByRefreshToken(refresh);

        //Refresh 토큰 Cookie 값 0
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
