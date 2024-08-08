package com.ee06.wooms.global.ai.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FailedRequestToGptException extends RuntimeException {
    private final ErrorCode errorCode;
    public FailedRequestToGptException() {
        this.errorCode = ErrorCode.FAILED_REQUEST_GPT;
    }
}
