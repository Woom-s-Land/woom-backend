package com.ee06.wooms.domain.wooms.dto;

import com.ee06.wooms.domain.enrollments.entity.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class WoomsEnrollRequest {
    @Schema(description = "참가 요청 상태", example = "ACCEPT", allowableValues = {"WAITING", "ACCEPT", "REFUSE"})
    private EnrollmentStatus status;
}
