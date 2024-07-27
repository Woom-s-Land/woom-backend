package com.ee06.wooms.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserGameInfo {
    private String email;
    private String nickname;
    private Integer costume;
}
