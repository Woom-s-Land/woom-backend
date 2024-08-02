package com.ee06.wooms.domain.letters.exception;

import com.ee06.wooms.domain.letters.exception.ex.NotInSameWoomsException;
import com.ee06.wooms.domain.letters.exception.ex.NotValidLetterException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsAlreadyMemberException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsAlreadyWaitingException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsNotValidEnrollmentException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsNotValidException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsNotValidInviteCodeException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsUserNotEnrolledException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsUserNotLeaderException;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.domain")
public class LetterExceptionHandler {

    @ExceptionHandler(NotInSameWoomsException.class)
    public ResponseEntity<Object> woomsAlreadyMemberException(NotInSameWoomsException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_IN_SAME_GROUP);
    }

    @ExceptionHandler(NotValidLetterException.class)
    public ResponseEntity<Object> notValidLetter(NotValidLetterException e) {
        return ErrorCodeUtils.build(ErrorCode.NOT_VALID_LETTER);
    }


}