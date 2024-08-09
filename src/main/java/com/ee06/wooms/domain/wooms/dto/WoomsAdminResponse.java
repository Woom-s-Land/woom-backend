package com.ee06.wooms.domain.wooms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class WoomsAdminResponse {
    final boolean isWoomsAdmin;
}
