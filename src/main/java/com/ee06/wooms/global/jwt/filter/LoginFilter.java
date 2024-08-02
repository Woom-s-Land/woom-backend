package com.ee06.wooms.global.jwt.filter;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.auth.Login;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.dto.RefreshToken;
import com.ee06.wooms.global.jwt.exception.CustomAuthenticationException;
import com.ee06.wooms.global.jwt.repository.RefreshTokenRepository;
import com.ee06.wooms.global.util.CookieUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JWTUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        super.setFilterProcessesUrl("/api/auth");
        Login login;
        try {
            login = new ObjectMapper().readValue(request.getInputStream(), Login.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String uuid = customUserDetails.getUuid();
        String name = customUserDetails.getUser().getNickname();
        String costume = String.valueOf(customUserDetails.getUser().getCostume());

        String accessToken = jwtUtil.generateAccessToken(uuid, name, costume, "");
        String refreshToken = jwtUtil.generateRefreshToken(uuid);

        addRefreshToken(uuid, refreshToken);

        CookieUtils.addCookie(response, "Authorization", accessToken, 216000);
        CookieUtils.addCookie(response, "refresh", refreshToken, 216000);

        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        authenticationEntryPoint.commence(request, response, new CustomAuthenticationException(ErrorCode.NOT_LOGIN_USER) {});
    }

    private void addRefreshToken(String uuid, String refreshToken) {
        RefreshToken token = RefreshToken
                .builder()
                .uuid(uuid)
                .refreshToken(refreshToken)
                .build();

        refreshTokenRepository.save(token);
    }
}