package com.ee06.wooms.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //======================== JWT 예외 ========================//
    NOT_FOUND_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "존재하지 않는 토큰입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 재발급 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    MAL_FORMED_TOKEN(HttpStatus.UNAUTHORIZED, "지원하지 않는 유형의 토큰입니다."),

    //======================== 사용자 예외 ========================//
    EXIST_USER(HttpStatus.INTERNAL_SERVER_ERROR, "이미 회원가입 된 이메일입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    NOT_FOUND_EMAIL_USER(HttpStatus.INTERNAL_SERVER_ERROR, "존재하지 않는 이메일입니다."),
    UN_AUTHENTICATED_USER(HttpStatus.FORBIDDEN, "허가되지 않는 접근입니다."),
    ACCESS_DENIED_USER(HttpStatus.FORBIDDEN, "허가되지 않는 사용자입니다."),

    //======================== OAuth 예외 ========================//
    NOT_FOUND_PLATFORM_SERVICE(HttpStatus.INTERNAL_SERVER_ERROR, "제공하지 않는 플랫폼입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
