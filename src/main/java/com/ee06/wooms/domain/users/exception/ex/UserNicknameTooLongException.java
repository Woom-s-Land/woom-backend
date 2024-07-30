package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserNicknameTooLongException extends RuntimeException {
    private final ErrorCode errorCode;
    public UserNicknameTooLongException() {
        this.errorCode = ErrorCode.TOO_LONG_NICKNAME_USER;
    }
}
