package com.ee06.wooms.global.ai.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FailedSwitchStateException extends RuntimeException {
    private final ErrorCode errorCode;

    public FailedSwitchStateException() {
        this.errorCode = ErrorCode.FAILED_SWITCH_STATE;
    }
}
