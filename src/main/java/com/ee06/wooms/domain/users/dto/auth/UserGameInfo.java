package com.ee06.wooms.domain.users.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserGameInfo {
    private String email;

    @NotBlank(message = "별명은 공백이 될 수 없습니다.")
    private String nickname;

    @NotBlank(message = "코스튬 번호는 공백이 될 수 없습니다.")
    private Integer costume;
}
