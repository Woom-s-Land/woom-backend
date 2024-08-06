package com.ee06.wooms.domain.wooms.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class WoomsLeaderNotLeftWoomsException extends RuntimeException{
    private final ErrorCode errorCode;
    public WoomsLeaderNotLeftWoomsException() {
        this.errorCode = ErrorCode.NOT_LEFT_WOOMS_LEADER;
    }
}
