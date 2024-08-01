package com.ee06.wooms.domain.letters.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_VALID_LETTER;

@Getter
public class NotValidLetterException extends RuntimeException{
    private final ErrorCode errorCode;

    public NotValidLetterException() {
        this.errorCode = NOT_VALID_LETTER;
    }
}