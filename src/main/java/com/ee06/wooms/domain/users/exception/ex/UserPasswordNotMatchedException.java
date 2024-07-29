package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_MATCHED_PASSWORD_USER;

@Getter
public class UserPasswordNotMatchedException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserPasswordNotMatchedException() {
        this.errorCode = NOT_MATCHED_PASSWORD_USER;
    }
}
