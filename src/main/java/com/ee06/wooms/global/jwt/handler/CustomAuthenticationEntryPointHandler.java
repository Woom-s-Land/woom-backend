package com.ee06.wooms.global.jwt.handler;

import com.ee06.wooms.global.jwt.dto.ErrorCode;
import com.ee06.wooms.global.jwt.filter.JWTFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null) {
            log.error("존재하지 않는 토큰");
            ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
            JWTFilter.setErrorResponse(response, errorCode);
        }
        else if (Objects.equals(authorization, ErrorCode.EXPIRED_TOKEN)) {
            log.error("만료된 토큰");
            ErrorCode errorCode = ErrorCode.EXPIRED_TOKEN;
            JWTFilter.setErrorResponse(response,errorCode);
        }
    }
}
