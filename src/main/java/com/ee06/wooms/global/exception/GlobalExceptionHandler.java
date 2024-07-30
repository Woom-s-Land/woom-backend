package com.ee06.wooms.global.exception;

import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return ErrorCodeUtils.build(HttpStatus.BAD_REQUEST, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
    }
}
