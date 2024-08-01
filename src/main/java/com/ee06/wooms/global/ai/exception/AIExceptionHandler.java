package com.ee06.wooms.global.ai.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.global.ai")
public class AIExceptionHandler {

    @ExceptionHandler(FailedRequestToGptException.class)
    public ResponseEntity<Object> invalidJwt(FailedRequestToGptException e) {
        return ErrorCodeUtils.build(ErrorCode.FAILED_REQUEST_GPT);
    }
}
