package com.ee06.wooms.domain.wooms.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class WoomInfoDto {
    private final Long woomId;
    private final UUID woomInviteCode;
    private final String woomTitle;
    private final LocalDateTime woomCreateTime;
    private final Long woomTotalUsers;

}