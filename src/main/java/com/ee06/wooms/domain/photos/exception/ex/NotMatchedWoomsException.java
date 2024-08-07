package com.ee06.wooms.domain.photos.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotMatchedWoomsException extends RuntimeException {
    private final ErrorCode errorCode;
    public NotMatchedWoomsException() {
        this.errorCode = ErrorCode.NOT_MATCHED_WOOMS;
    }
}
