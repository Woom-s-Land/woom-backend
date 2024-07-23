package com.ee06.wooms.domain.users.dto.oauth;

import com.ee06.wooms.domain.users.entity.SocialProvider;

import java.util.Map;

public class GithubResponse implements OAuth2Response {
    private final Map<String, Object> attributes;

    public GithubResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return SocialProvider.GITHUB.getSocialProvider();
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}
