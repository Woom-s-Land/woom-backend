package com.ee06.wooms.domain.wooms.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_VALID_WOOMS;

@Getter
public class WoomsNotValidException extends RuntimeException {
    private final ErrorCode errorCode;

    public WoomsNotValidException() {
        this.errorCode = NOT_VALID_WOOMS;
    }
}

