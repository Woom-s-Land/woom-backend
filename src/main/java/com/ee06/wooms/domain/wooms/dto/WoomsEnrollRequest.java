package com.ee06.wooms.domain.wooms.dto;

import com.ee06.wooms.domain.enrollments.entity.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class WoomsEnrollRequest {
    private EnrollmentStatus status;
}
