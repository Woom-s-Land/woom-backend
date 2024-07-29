package com.ee06.wooms.global.util;

import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;

public class ErrorCodeUtils {
    private ErrorCodeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseEntity<Object> build(ErrorCode errorCode,String message) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(errorCode.getHttpStatus(), message));
    }

    public static ResponseEntity<Object> build(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(errorCode.getHttpStatus(), errorCode.getMessage()));
    }
}
