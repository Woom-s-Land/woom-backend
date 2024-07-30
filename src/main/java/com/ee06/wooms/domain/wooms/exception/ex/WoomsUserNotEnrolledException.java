package com.ee06.wooms.domain.wooms.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.FORBIDDEN_USER_NOT_ENROLLED;

@Getter
public class WoomsUserNotEnrolledException extends RuntimeException {
    private final ErrorCode errorCode;

    public WoomsUserNotEnrolledException() {
        this.errorCode = FORBIDDEN_USER_NOT_ENROLLED;
    }
}

