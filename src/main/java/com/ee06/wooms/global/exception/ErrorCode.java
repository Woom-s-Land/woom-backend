package com.ee06.wooms.global.exception;

import com.ee06.wooms.global.oauth.exception.NotFoundPlatformException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //======================== JWT 예외 ========================//
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),

    //======================== 사용자 예외 ========================//
    EXIST_USER(HttpStatus.INTERNAL_SERVER_ERROR, "이미 회원가입 된 이메일입니다."),

    //======================== OAuth 예외 ========================//
    NOT_FOUND_PLATFORM_SERVICE(HttpStatus.INTERNAL_SERVER_ERROR, "제공하지 않는 플랫폼입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
