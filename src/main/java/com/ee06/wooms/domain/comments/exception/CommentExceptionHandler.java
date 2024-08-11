package com.ee06.wooms.domain.comments.exception;

import com.ee06.wooms.domain.comments.exception.ex.ExistWroteCommentException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsAlreadyMemberException;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.domain.comments")
public class CommentExceptionHandler {
    @ExceptionHandler(ExistWroteCommentException.class)
    public ResponseEntity<Object> existWroteCommentException(ExistWroteCommentException e) {
        return ErrorCodeUtils.build(e.getErrorCode());
    }
}
