package com.ee06.wooms.domain.photos.exception;

import com.ee06.wooms.domain.photos.exception.ex.FailedCreatePhotoException;
import com.ee06.wooms.domain.photos.exception.ex.NotFoundPhotoException;
import com.ee06.wooms.domain.photos.exception.ex.NotMatchedUserException;
import com.ee06.wooms.domain.photos.exception.ex.NotMatchedWoomsException;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.domain.photos")
public class PhotoExceptionHandler {
    @ExceptionHandler(NotMatchedUserException.class)
    public ResponseEntity<Object> existWroteCommentException(NotMatchedUserException e) {
        return ErrorCodeUtils.build(e.getErrorCode());
    }

    @ExceptionHandler(NotFoundPhotoException.class)
    public ResponseEntity<Object> notFoundPhotoException(NotFoundPhotoException e) {
        return ErrorCodeUtils.build(e.getErrorCode());
    }

    @ExceptionHandler(NotMatchedWoomsException.class)
    public ResponseEntity<Object> notMatchedWoomsException(NotMatchedWoomsException e) {
        return ErrorCodeUtils.build(e.getErrorCode());
    }

    @ExceptionHandler(FailedCreatePhotoException.class)
    public ResponseEntity<Object> failedCreatePhotoException(FailedCreatePhotoException e) {
        return ErrorCodeUtils.build(e.getErrorCode());
    }
}