package com.ee06.wooms.global.oauth.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundPlatformException extends RuntimeException {
    final ErrorCode errorCode;
}

