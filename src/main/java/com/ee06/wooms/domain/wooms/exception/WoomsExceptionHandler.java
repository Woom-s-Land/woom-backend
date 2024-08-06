package com.ee06.wooms.domain.wooms.exception;

import com.ee06.wooms.domain.wooms.exception.ex.*;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.domain")
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

    @ExceptionHandler(WoomsUserNotLeaderException.class)
    public ResponseEntity<Object> woomsUserNotLeaderException(WoomsUserNotLeaderException e) {
        return ErrorCodeUtils.build(ErrorCode.FORBIDDEN_USER_NOT_LEADER);
    }

    @ExceptionHandler(WoomsNotValidEnrollmentException.class)
    public ResponseEntity<Object> woomsNotValidEnrollmentException(WoomsNotValidEnrollmentException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_VALID_ENROLLMENT);
    }

    @ExceptionHandler(WoomsNotValidException.class)
    public ResponseEntity<Object> woomsNotValidException(WoomsNotValidException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_VALID_WOOMS);
    }

    @ExceptionHandler(WoomsEnrollmentLimitExceededException.class)
    public ResponseEntity<Object> woomsNotValidException(WoomsEnrollmentLimitExceededException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_VALID_ENROLLMENT_LIMIT);
    }

    @ExceptionHandler(WoomsLeaderNotLeftWoomsException.class)
    public ResponseEntity<Object> woomsLeaderNotLeaveException(WoomsLeaderNotLeftWoomsException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_LEFT_WOOMS_LEADER);
    }

    @ExceptionHandler(WoomsNotAllowedUserException.class)
    public ResponseEntity<Object> woomsUserNotAllowed(WoomsNotAllowedUserException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_ALLOWED_USER);
    }
}