package com.ee06.wooms.global.exception;

import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindingException.class)
    public ResponseEntity<Object> bindingError(BindingException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ErrorCodeUtils.build(errorCode, e.getMessage());
    }
}
