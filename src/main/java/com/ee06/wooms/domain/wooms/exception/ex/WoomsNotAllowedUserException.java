package com.ee06.wooms.domain.wooms.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class WoomsNotAllowedUserException extends RuntimeException{
    private final ErrorCode errorCode;
    public WoomsNotAllowedUserException() {
        errorCode = ErrorCode.NOT_ALLOWED_USER;
    }
}
