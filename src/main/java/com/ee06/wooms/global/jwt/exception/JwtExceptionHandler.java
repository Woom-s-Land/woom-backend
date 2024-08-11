package com.ee06.wooms.global.jwt.exception;

import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.ErrorCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ee06.wooms.global.jwt")
public class JwtExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> invalidJwt(InvalidTokenException e) {
        return ErrorCodeUtils.build(ErrorCode.INVALID_TOKEN);
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<Object> invalidRefreshJwt(InvalidRefreshTokenException e) {
        return ErrorCodeUtils.build(ErrorCode.INVALID_REFRESH_TOKEN);
    }

    @ExceptionHandler(ExpiredRefreshTokenException.class)
    public ResponseEntity<Object> expiredRefreshJwt(ExpiredRefreshTokenException e) {
        return ErrorCodeUtils.build(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }

    @ExceptionHandler(ReIssueTokenResponse.class)
    public ResponseEntity<Object> reIssueToken(ReIssueTokenResponse e) {
        return ErrorCodeUtils.build(ErrorCode.RE_ISSUE_TOKEN);
    }
}
