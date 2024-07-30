package com.ee06.wooms.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class UserInfoDto {
    private final UUID uuid;
    private final String name;
    private final String nickname;
    private final Integer costume;
}
