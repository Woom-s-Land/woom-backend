package com.ee06.wooms.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BindingException extends RuntimeException{
    final ErrorCode errorCode;
    final String message;
    public BindingException(String message) {
        this.errorCode = ErrorCode.BINDING_ERROR;
        this.message = message;
    }
}
