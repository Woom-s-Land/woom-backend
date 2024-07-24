package com.ee06.wooms.global.jwt.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.global.jwt")
public class JwtExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> invalidJwt(InvalidTokenException e) {
        return ErrorCodeUtils.build(ErrorCode.INVALID_TOKEN);
    }
}
