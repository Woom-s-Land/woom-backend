package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_SENT_EMAIL_USER;

@Getter
public class UserNotSentEmailException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserNotSentEmailException() {
        this.errorCode = NOT_SENT_EMAIL_USER;
    }
}
