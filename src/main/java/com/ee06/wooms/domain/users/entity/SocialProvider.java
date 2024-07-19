package com.ee06.wooms.domain.users.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialProvider {
    MATTERMOST("mattermost"),
    GOOGLE("google"),
    GITHUB("github");

    private final String socialProvider;

}
