package com.ee06.wooms.domain.wooms.dto;

import com.ee06.wooms.domain.users.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class WoomsDetailInfoDto {
    private final WoomsDto woomsDto;
    private final List<UserInfoDto> userInfoDtoList;
}
