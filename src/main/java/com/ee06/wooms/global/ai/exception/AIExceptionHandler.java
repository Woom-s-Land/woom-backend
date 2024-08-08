package com.ee06.wooms.global.ai.exception;

import com.ee06.wooms.global.ai.exception.ex.FailedConvertFileException;
import com.ee06.wooms.global.ai.exception.ex.FailedRequestToGptException;
import com.ee06.wooms.global.ai.exception.ex.FailedSwitchStateException;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.global.ai")
public class AIExceptionHandler {

    @ExceptionHandler(FailedRequestToGptException.class)
    public ResponseEntity<Object> failedRequestToGpt(FailedRequestToGptException e) {
        return ErrorCodeUtils.build(ErrorCode.FAILED_REQUEST_GPT);
    }

    @ExceptionHandler(FailedConvertFileException.class)
    public ResponseEntity<Object> failedConvertAudioFile(FailedConvertFileException e) {
        return ErrorCodeUtils.build(ErrorCode.FAILED_CONVERT_MP3_FILE);
    }

    @ExceptionHandler(FailedSwitchStateException.class)
    public ResponseEntity<Object> failedSwitchState(FailedSwitchStateException e) {
        return ErrorCodeUtils.build(ErrorCode.FAILED_SWITCH_STATE);
    }
}
