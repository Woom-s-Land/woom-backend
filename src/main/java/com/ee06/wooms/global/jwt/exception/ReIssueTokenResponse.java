package com.ee06.wooms.global.jwt.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ReIssueTokenResponse extends RuntimeException{
    private final ErrorCode errorCode;
    public ReIssueTokenResponse() {
        this.errorCode = ErrorCode.RE_ISSUE_TOKEN;
    }
}