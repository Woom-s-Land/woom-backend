package com.ee06.wooms.global.jwt.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ExpiredRefreshTokenException extends RuntimeException {
    private final ErrorCode errorCode;
    public ExpiredRefreshTokenException() {
        this.errorCode = ErrorCode.EXPIRED_REFRESH_TOKEN;
    }
}
