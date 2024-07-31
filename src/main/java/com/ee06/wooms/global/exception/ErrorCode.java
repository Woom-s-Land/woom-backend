package com.ee06.wooms.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCode {

    //======================== Binding 예외 ========================//
    BINDING_ERROR(HttpStatus.BAD_REQUEST),

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
    NOT_SENT_EMAIL_USER(HttpStatus.INTERNAL_SERVER_ERROR, "이메일을 전송하지 못하였습니다.\n정확한 이메일을 입력해주세요."),
    EMAIL_EXPIRED_USER(HttpStatus.BAD_GATEWAY, "만료된 인증 코드입니다. 다시 인증해주세요."),
    NOT_MATCHED_EMAIL_CODE_USER(HttpStatus.UNAUTHORIZED, "일치하지 않는 코드입니다. 다시 확인해주세요."),
    NOT_MATCHED_PASSWORD_USER(HttpStatus.UNAUTHORIZED, "현재 비밀번호가 일치하지 않습니다. 다시 확인해주세요."),
    NOT_LOGIN_USER(HttpStatus.UNAUTHORIZED, "로그인 할 수 없습니다. 아이디 혹은 비밀번호를 다시 확인해주세요."),
    TOO_LONG_NICKNAME_USER(HttpStatus.BAD_REQUEST, "별명이 너무 깁니다. 다시 입력해주세요."),

    //======================== Wooms 예외 ========================//
    NOT_VALID_WOOMS_INVITE_CODE(HttpStatus.NOT_FOUND, "유효하지 않은 초대 코드입니다."),
    CONFLICT_ALREADY_WAITING(HttpStatus.CONFLICT, "이미 대기 중인 방입니다."),
    CONFLICT_ALREADY_MEMBER(HttpStatus.CONFLICT, "이미 가입한 방입니다."),
    FORBIDDEN_USER_NOT_ENROLLED(HttpStatus.FORBIDDEN, "가입하지 않은 방입니다."),
    FORBIDDEN_USER_NOT_LEADER(HttpStatus.FORBIDDEN, "유저는 Wooms의 리더가 아닙니다."),
    NOT_VALID_ENROLLMENT(HttpStatus.NOT_FOUND, "유효하지 않은 수락 요청입니다."),
    NOT_VALID_WOOMS(HttpStatus.NOT_FOUND, "유효하지 않은 Wooms 입니다.."),

    //======================== OAuth 예외 ========================//
    NOT_FOUND_PLATFORM_SERVICE(HttpStatus.INTERNAL_SERVER_ERROR, "제공하지 않는 플랫폼입니다."),


    //======================= Comment 예외 ======================//
    EXIST_WROTE_COMMENT(HttpStatus.BAD_REQUEST, "오늘 이미 방명록을 작성하였습니다.");


    private final HttpStatus httpStatus;
    private String message;
}
