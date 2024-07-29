package com.ee06.wooms.domain.wooms.exception.ex;

import static com.ee06.wooms.global.exception.ErrorCode.CONFLICT_ALREADY_WAITING;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class WoomsAlreadyWaitingException extends RuntimeException {
    private final ErrorCode errorCode;

    public WoomsAlreadyWaitingException() {
        this.errorCode = CONFLICT_ALREADY_WAITING;
    }
}

