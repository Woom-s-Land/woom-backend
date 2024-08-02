package com.ee06.wooms.domain.wooms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WoomsMandateAdminRequest {
    private String userUuid;
}
