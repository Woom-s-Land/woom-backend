package com.ee06.wooms.domain.wooms.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_VALID_ENROLLMENT_LIMIT;

@Getter
public class WoomsEnrollmentLimitExceededException extends RuntimeException {
    private final ErrorCode errorCode;

    public WoomsEnrollmentLimitExceededException() {
        this.errorCode = NOT_VALID_ENROLLMENT_LIMIT;
    }
}

