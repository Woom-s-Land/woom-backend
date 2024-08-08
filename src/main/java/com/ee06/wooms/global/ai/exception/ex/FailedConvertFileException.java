package com.ee06.wooms.global.ai.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FailedConvertFileException extends RuntimeException {
    private final ErrorCode errorCode;
    public FailedConvertFileException() {
        this.errorCode = ErrorCode.FAILED_CONVERT_MP3_FILE;
    }
}
