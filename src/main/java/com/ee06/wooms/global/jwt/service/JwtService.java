package com.ee06.wooms.global.jwt.service;

import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.dto.RefreshToken;
import com.ee06.wooms.global.jwt.exception.InvalidRefreshTokenException;
import com.ee06.wooms.global.jwt.exception.InvalidTokenException;
import com.ee06.wooms.global.jwt.repository.RefreshTokenRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    public CommonResponse re(HttpServletRequest request, HttpServletResponse response) {
        String token = getToken(request);
        if(!jwtUtil.validateToken(token) || !Objects.equals(jwtUtil.getSubject(token), "refresh-token")){
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }

        if(refreshTokenRepository.existsByRefreshToken(token)) {
            throw new InvalidRefreshTokenException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        String uuid = jwtUtil.getUuid(token);
        String name = jwtUtil.getName(token);

        String newAccessToken = jwtUtil.generateAccessToken(uuid, name);
        String newRefreshToken = jwtUtil.generateRefreshToken(uuid, name);

        refreshTokenRepository.deleteByRefreshToken(token);
        addRefreshToken(uuid, token);

        response.addCookie(createCookie("Authorization", newAccessToken));
        response.addCookie(createCookie("refresh", newRefreshToken));

        return new CommonResponse("ok");
    }

    private static String getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> Objects.equals("refresh", cookie.getName()))
                        .map(Cookie::getValue)
                        .findAny())
                .orElse(null);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
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
