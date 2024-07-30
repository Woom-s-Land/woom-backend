package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.EXIST_USER;

@Getter
public class UserExistException extends RuntimeException {
    final ErrorCode errorCode;

    public UserExistException() {
        this.errorCode = EXIST_USER;
    }
}
