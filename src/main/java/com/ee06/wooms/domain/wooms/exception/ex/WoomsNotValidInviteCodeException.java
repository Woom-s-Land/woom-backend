package com.ee06.wooms.domain.wooms.exception.ex;

import static com.ee06.wooms.global.exception.ErrorCode.NOT_FOUND_PLATFORM_SERVICE;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class WoomsNotValidInviteCodeException extends RuntimeException {
    private final ErrorCode errorCode;

    public WoomsNotValidInviteCodeException() {
        this.errorCode = NOT_FOUND_PLATFORM_SERVICE;
    }
}

