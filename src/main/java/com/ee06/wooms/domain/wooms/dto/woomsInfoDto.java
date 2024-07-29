package com.ee06.wooms.domain.wooms.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class woomsInfoDto {
    private final Long woomsId;
    private final UUID woomsInviteCode;
    private final String woomsTitle;
    private final LocalDateTime woomsCreateTime;
    private final Long woomsTotalUsers;

}