package com.ee06.wooms.global.jwt.filter;

import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.exception.InvalidRefreshTokenException;
import com.ee06.wooms.global.jwt.exception.InvalidTokenException;
import com.ee06.wooms.global.jwt.repository.RefreshTokenRepository;
import com.ee06.wooms.global.util.CookieUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {
    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();
        if (!requestUri.matches("^\\/api/auth$") || !Objects.equals(requestMethod, "DELETE")) {
            chain.doFilter(request, response);
            return;
        }

        //get refresh token
        String access = CookieUtils.getCookie(request, "Authorization");
        String refresh = CookieUtils.getCookie(request, "refresh");

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

        refreshTokenRepository.deleteByRefreshToken(refresh);

        CookieUtils.addCookie(response, "refresh", refresh, 0);
        CookieUtils.addCookie(response, "Authorization", access, 0);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}