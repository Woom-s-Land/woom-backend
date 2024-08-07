package com.ee06.wooms.domain.photos.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotMatchedUserException extends RuntimeException {
    private final ErrorCode errorCode;
    public NotMatchedUserException() {
        this.errorCode = ErrorCode.NOT_MATCHED_USER;
    }
}
