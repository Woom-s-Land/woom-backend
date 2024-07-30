package com.ee06.wooms.domain.wooms.exception;

import com.ee06.wooms.domain.wooms.exception.ex.WoomsAlreadyMemberException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsAlreadyWaitingException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsNotValidInviteCodeException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsUserNotEnrolledException;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.domain.wooms")
public class WoomsExceptionHandler {

    @ExceptionHandler(WoomsAlreadyMemberException.class)
    public ResponseEntity<Object> woomsAlreadyMemberException(WoomsAlreadyMemberException e) {
        return ErrorCodeUtils.build(ErrorCode.CONFLICT_ALREADY_MEMBER);
    }

    @ExceptionHandler(WoomsAlreadyWaitingException.class)
    public ResponseEntity<Object> woomsAlreadyWaitingException(WoomsAlreadyWaitingException e) {
        return ErrorCodeUtils.build(ErrorCode.CONFLICT_ALREADY_WAITING);
    }

    @ExceptionHandler(WoomsNotValidInviteCodeException.class)
    public ResponseEntity<Object> woomsNotInvalidInviteCodeException(WoomsNotValidInviteCodeException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_VALID_WOOMS_INVITE_CODE);
    }

    @ExceptionHandler(WoomsUserNotEnrolledException.class)
    public ResponseEntity<Object> woomsUserNotEnrolledException(WoomsUserNotEnrolledException e) {
        return ErrorCodeUtils.build(ErrorCode.FORBIDDEN_USER_NOT_ENROLLED);
    }
}