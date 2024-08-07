package com.ee06.wooms.domain.photos.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FailedCreatePhotoException extends RuntimeException {
    private final ErrorCode errorCode;
    public FailedCreatePhotoException() {
        this.errorCode = ErrorCode.FAILED_CREATE_PHOTO;
    }
}
