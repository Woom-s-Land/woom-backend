package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_MATCHED_EMAIL_CODE_USER;

@Getter
public class UserEmailCodeNotMatchedException extends RuntimeException{
    private final ErrorCode errorCode;

    public UserEmailCodeNotMatchedException() {
        this.errorCode = NOT_MATCHED_EMAIL_CODE_USER;
    }
}
