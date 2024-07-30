package com.ee06.wooms.domain.users.service;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.oauth.GithubResponse;
import com.ee06.wooms.domain.users.dto.oauth.GoogleResponse;
import com.ee06.wooms.domain.users.dto.oauth.OAuth2Response;
import com.ee06.wooms.domain.users.entity.SocialProvider;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.entity.UserStatus;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.global.oauth.exception.NotFoundPlatformException;
import com.ee06.wooms.global.util.RandomHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response =
                provideOAuth(userRequest.getClientRegistration().getRegistrationId(), oAuth2User)
                .orElseThrow(NotFoundPlatformException::new);

        String userEmail = oAuth2Response.getEmail();

        Optional<User> existUser = userRepository.findByEmail(userEmail);
        if(existUser.isPresent()) {
            return new CustomUserDetails(existUser.get(), oAuth2User.getAttributes());
        }

        User user = setUserInfo(userEmail, oAuth2Response, registrationId);
        userRepository.save(user);
        return new CustomUserDetails(user, oAuth2User.getAttributes());
    }

    private User setUserInfo(String userEmail, OAuth2Response oAuth2Response, String registrationId) {
        return User.builder()
                .email(userEmail)
                .name(oAuth2Response.getName())
                .nickname(RandomHelper.generateNickname())
                .socialProvider(SocialProvider.valueOf(registrationId.toUpperCase()))
                .status(UserStatus.ACTIVE)
                .costume(1)
                .build();
    }

    private static Optional<OAuth2Response> provideOAuth(String registrationId, OAuth2User oAuth2User) {
        if(Objects.equals(registrationId, "google")) {
            return Optional.of(new GoogleResponse(oAuth2User.getAttributes()));
        }
        if(Objects.equals(registrationId, "github")) {
            return Optional.of(new GithubResponse(oAuth2User.getAttributes()));
        }
        return Optional.empty();
    }
}
