package com.ee06.wooms.domain.users.service;

import com.ee06.wooms.domain.users.dto.CustomOAuth2User;
import com.ee06.wooms.domain.users.dto.GithubResponse;
import com.ee06.wooms.domain.users.dto.GoogleResponse;
import com.ee06.wooms.domain.users.dto.OAuth2Response;
import com.ee06.wooms.domain.users.entity.SocialProvider;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.entity.UserStatus;
import com.ee06.wooms.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = provideOAuth(userRequest.getClientRegistration().getRegistrationId(), oAuth2User);

        if (oAuth2Response == null) return null;
        String userEmail = oAuth2Response.getEmail();
        User existUser = userRepository.findByUserEmail(userEmail);

        if(existUser == null) {
            User user = User.builder()
                    .userUuid(UUID.randomUUID())
                    .userEmail(userEmail)
                    .userPassword(bCryptPasswordEncoder.encode("password"))
                    .userName(oAuth2Response.getName())
                    .socialProvider(SocialProvider.valueOf(registrationId.toUpperCase()))
                    .userStatus(UserStatus.ACTIVE)
                    .userCostume(1)
                    .build();

            userRepository.save(user);

            return new CustomOAuth2User(user, oAuth2User.getAttributes());
        } else {
            userRepository.save(existUser);

            return new CustomOAuth2User(existUser, oAuth2User.getAttributes());
        }
    }

    private static OAuth2Response provideOAuth(String registrationId, OAuth2User oAuth2User) {
        OAuth2Response oAuth2Response;
        if(Objects.equals("google", registrationId)) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else if(Objects.equals("github", registrationId)) {
            oAuth2Response = new GithubResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }
        return oAuth2Response;
    }
}
