package com.ee06.wooms.domain.users.dto.auth;

import com.ee06.wooms.global.util.RegExpUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class UserGameInfo {
    private String email;

    @NotBlank(message = "별명은 공백이 될 수 없습니다.")
    @Pattern(message = "별명은 한글, 알파벳, 숫자로만 구성할 수 있습니다.", regexp = RegExpUtils.NICKNAME_EXP)
    private String nickname;

    @NotNull(message = "코스튬 번호는 공백이 될 수 없습니다.")
    private Integer costume;

    private UUID userUuid;
}
