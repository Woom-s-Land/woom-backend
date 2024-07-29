package com.ee06.wooms.domain.wooms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WoomsCreateRequestDto {
    private String woomsTitle;
}