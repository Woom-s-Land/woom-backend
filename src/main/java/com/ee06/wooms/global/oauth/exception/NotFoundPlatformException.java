package com.ee06.wooms.global.oauth.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_FOUND_PLATFORM_SERVICE;

@Getter
public class NotFoundPlatformException extends RuntimeException {
    final ErrorCode errorCode;

    public NotFoundPlatformException() {
        this.errorCode = NOT_FOUND_PLATFORM_SERVICE;
    }
}

