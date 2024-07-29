package com.ee06.wooms.domain.wooms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@Builder
public class WoomsInfoDto {
    private final Long woomsId;
    private final UUID woomsInviteCode;
    private final String woomsTitle;
    private final LocalDateTime woomsCreateTime;
    private final Long woomsTotalUsers;

}