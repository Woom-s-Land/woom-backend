package com.ee06.wooms.domain.users.dto;

import com.ee06.wooms.domain.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    private final User user;
    private final Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 추후 사용자 역할 적용 시 구현
        return new ArrayList<>();
    }

    @Override
    public String getName() {
        return user.getUserName();
    }

    public String getUuid() {
        return String.valueOf(user.getUserUuid());
    }
}
