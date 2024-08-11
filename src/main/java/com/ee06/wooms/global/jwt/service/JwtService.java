package com.ee06.wooms.global.jwt.service;

import com.ee06.wooms.domain.users.dto.auth.UserDto;
import com.ee06.wooms.domain.users.service.UserService;
import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.dto.RefreshToken;
import com.ee06.wooms.global.jwt.exception.ExpiredRefreshTokenException;
import com.ee06.wooms.global.jwt.exception.InvalidRefreshTokenException;
import com.ee06.wooms.global.jwt.exception.InvalidTokenException;
import com.ee06.wooms.global.jwt.exception.ReIssueTokenResponse;
import com.ee06.wooms.global.jwt.repository.RefreshTokenRepository;
import com.ee06.wooms.global.util.CookieUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    public CommonResponse issueRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookie(request, "refresh");

        validateRefreshToken(refreshToken);

        String uuid = jwtUtil.getUuid(refreshToken);
        UserDto userDto = userService.findById(UUID.fromString(uuid));

        String nickname = userDto.getNickname();
        String costume = String.valueOf(userDto.getCostume());

        String newAccessToken = jwtUtil.generateAccessToken(uuid, nickname, costume, "");
        String newRefreshToken = jwtUtil.generateRefreshToken(uuid);

        refreshTokenRepository.deleteById(refreshToken);
        addRefreshToken(uuid, newRefreshToken);

        CookieUtils.addCookie(response, "Authorization", newAccessToken, 216000);
        CookieUtils.addCookie(response, "refresh", newRefreshToken, 216000);

        throw new ReIssueTokenResponse();
    }

    private void validateRefreshToken(String refreshToken) {
        try {
            jwtUtil.validateToken(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new ExpiredRefreshTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }

        if (!Objects.equals(jwtUtil.getSubject(refreshToken), "refresh-token")){
            throw new InvalidTokenException();
        }

        if (refreshTokenRepository.findById(refreshToken).isEmpty()) {
            throw new InvalidRefreshTokenException();
        }
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
