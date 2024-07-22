package com.ee06.wooms.global.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Token {
    private String accessToken;
    private String refreshToken;
    private String grantType;
}
