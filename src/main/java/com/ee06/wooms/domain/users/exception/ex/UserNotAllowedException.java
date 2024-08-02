package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_MATCHED_PASSWORD_USER;
import static com.ee06.wooms.global.exception.ErrorCode.UN_AUTHENTICATED_USER;

@Getter
public class UserNotAllowedException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserNotAllowedException() {
        this.errorCode = UN_AUTHENTICATED_USER;
    }
}
