package com.ee06.wooms.domain.users.exception.ex;

import com.ee06.wooms.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserEmailCodeNotMatchedException extends RuntimeException{
    private final ErrorCode errorCode;
}
