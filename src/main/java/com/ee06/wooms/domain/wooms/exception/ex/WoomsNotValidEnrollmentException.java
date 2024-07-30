package com.ee06.wooms.domain.wooms.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.FORBIDDEN_USER_NOT_LEADER;
import static com.ee06.wooms.global.exception.ErrorCode.NOT_VALID_ENROLLMENT;

@Getter
public class WoomsNotValidEnrollmentException extends RuntimeException {
    private final ErrorCode errorCode;

    public WoomsNotValidEnrollmentException() {
        this.errorCode = NOT_VALID_ENROLLMENT;
    }
}

