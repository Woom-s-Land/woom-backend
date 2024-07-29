package com.ee06.wooms.domain.wooms.exception.ex;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_VALID_WOOMS_INVITE_CODE;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class WoomsAlreadyMemberException extends RuntimeException {
    private final ErrorCode errorCode;

    public WoomsAlreadyMemberException() {
        this.errorCode = NOT_VALID_WOOMS_INVITE_CODE;
    }
}

