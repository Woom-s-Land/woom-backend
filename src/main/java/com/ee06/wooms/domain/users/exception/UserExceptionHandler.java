package com.ee06.wooms.domain.users.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.domain.users")
public class UserExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Object> existUser(UserExistException e) {
        return ErrorCodeUtils.build(ErrorCode.EXIST_USER);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> notFoundUser(UserNotFoundException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_FOUND_USER);
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<Object> notFoundEmailUser(UserEmailNotFoundException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_FOUND_EMAIL_USER);
    }

    @ExceptionHandler(UserNotSentEmailException.class)
    public ResponseEntity<Object> notSendingEmail(UserNotSentEmailException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_SENT_EMAIL_USER);
    }

}
