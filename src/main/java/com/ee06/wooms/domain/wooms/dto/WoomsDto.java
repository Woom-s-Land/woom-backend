package com.ee06.wooms.domain.wooms.dto;

import java.util.Map;
import java.util.UUID;

import com.ee06.wooms.domain.wooms.entity.MapColorStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class WoomsDto {
    private final Long woomsId;
    private final UUID woomsInviteCode;
    private final UUID woomsLeaderUuid;
    private final String woomsTitle;
    private final MapColorStatus mapColorStatus;
}
