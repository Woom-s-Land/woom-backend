package com.ee06.wooms.global.jwt.service;

import com.ee06.wooms.domain.users.dto.auth.UserDto;
import com.ee06.wooms.domain.users.service.UserService;
import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.dto.RefreshToken;
import com.ee06.wooms.global.jwt.exception.InvalidRefreshTokenException;
import com.ee06.wooms.global.jwt.exception.InvalidTokenException;
import com.ee06.wooms.global.jwt.repository.RefreshTokenRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    public CommonResponse issueRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = getToken(request);
        if (!jwtUtil.validateToken(refreshToken) || !Objects.equals(jwtUtil.getSubject(refreshToken), "refresh-token"))
            throw new InvalidTokenException();

        if (refreshTokenRepository.findById(refreshToken).isEmpty())
            throw new InvalidRefreshTokenException();

        String uuid = jwtUtil.getUuid(refreshToken);
        UserDto userDto = userService.findById(UUID.fromString(uuid));

        String nickname = userDto.getNickname();
        String costume = String.valueOf(userDto.getCostume());

        String newAccessToken = jwtUtil.generateAccessToken(uuid, nickname, costume);
        String newRefreshToken = jwtUtil.generateRefreshToken(uuid);

        refreshTokenRepository.deleteByRefreshToken(refreshToken);
        addRefreshToken(uuid, newRefreshToken);

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
