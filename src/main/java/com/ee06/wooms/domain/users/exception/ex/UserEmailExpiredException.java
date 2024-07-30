package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.EMAIL_EXPIRED_USER;

@Getter
public class UserEmailExpiredException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserEmailExpiredException() {
        this.errorCode = EMAIL_EXPIRED_USER;
    }
}
