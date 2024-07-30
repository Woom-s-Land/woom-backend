package com.ee06.wooms.global.util;

import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ErrorCodeUtils {
    private ErrorCodeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseEntity<Object> build(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(status, message));
    }

    public static ResponseEntity<Object> build(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(errorCode.getHttpStatus(), errorCode.getMessage()));
    }
}
