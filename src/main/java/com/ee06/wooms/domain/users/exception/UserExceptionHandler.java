package com.ee06.wooms.domain.users.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Object> existUser(UserExistException e) {
        return ErrorCodeUtils.build(ErrorCode.EXIST_USER);
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<Object> notFoundUser(UserEmailNotFoundException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_FOUND_USER);
    }
}
