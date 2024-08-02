package com.ee06.wooms.global.aws.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.global.aws")
public class AwsExceptionHandler {
    @ExceptionHandler(FileUploadFailedException.class)
    public ResponseEntity<Object> fileUploadFailed(FileUploadFailedException e) {
        return ErrorCodeUtils.build(ErrorCode.FAILED_UPLOAD_FILE);
    }
}
