package com.ee06.wooms.global.oauth;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.dto.RefreshToken;
import com.ee06.wooms.global.jwt.repository.RefreshTokenRepository;
import com.ee06.wooms.global.util.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${spring.front.uri}")
    private String frontURI;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String uuid = customUserDetails.getUuid();
        String nickname = customUserDetails.getNickname();
        String costume = customUserDetails.getCostume();

        String accessToken = jwtUtil.generateAccessToken(uuid, nickname, costume, "");
        String refreshToken = jwtUtil.generateRefreshToken(uuid);
        CookieUtils.addCookie(response, "Authorization", accessToken, 216000);
        CookieUtils.addCookie(response, "refresh", refreshToken, 216000);

        addRefreshToken(uuid, refreshToken);

        response.sendRedirect(frontURI);
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
