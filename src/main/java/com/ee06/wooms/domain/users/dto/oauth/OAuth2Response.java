package com.ee06.wooms.domain.users.dto.oauth;

public interface OAuth2Response {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
