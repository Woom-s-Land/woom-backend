package com.ee06.wooms.domain.users.exception.ex;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_FOUND_USER;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserNotFoundException() {
        this.errorCode = NOT_FOUND_USER;
    }
}
