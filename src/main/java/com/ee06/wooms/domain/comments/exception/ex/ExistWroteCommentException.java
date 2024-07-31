package com.ee06.wooms.domain.comments.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ExistWroteCommentException extends RuntimeException {
    final ErrorCode errorCode;

    public ExistWroteCommentException() {
        this.errorCode = ErrorCode.EXIST_WROTE_COMMENT;
    }
}
