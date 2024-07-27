package com.ee06.wooms.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo {
    private String email;
    private String nickname;
}
