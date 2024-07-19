package com.ee06.wooms.domain.enrollments.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnrollmentStatus {
    WAITING("대기"),
    ACCEPT("수락"),
    REFUSE("거절");

    private final String requestStatus;
}