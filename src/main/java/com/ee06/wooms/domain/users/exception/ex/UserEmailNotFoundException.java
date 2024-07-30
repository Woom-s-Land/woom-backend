package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_FOUND_EMAIL_USER;

@Getter
public class UserEmailNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserEmailNotFoundException() {
        this.errorCode = NOT_FOUND_EMAIL_USER;
    }
}
