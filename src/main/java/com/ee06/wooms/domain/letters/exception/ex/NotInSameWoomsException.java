package com.ee06.wooms.domain.letters.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_IN_SAME_GROUP;

@Getter
public class NotInSameWoomsException extends RuntimeException{
    private final ErrorCode errorCode;

    public NotInSameWoomsException() {
        this.errorCode = NOT_IN_SAME_GROUP;
    }
}

