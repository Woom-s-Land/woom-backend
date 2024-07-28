package com.ee06.wooms.domain.wooms.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class WoomDto {
    private final Long woomId;
    private final UUID woomInviteCode;
    private final String woomTitle;
}
