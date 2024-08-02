package com.ee06.wooms.global.aws.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FileUploadFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    public FileUploadFailedException() {
        this.errorCode = ErrorCode.FAILED_UPLOAD_FILE;
    }
}
