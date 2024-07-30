package com.ee06.wooms.domain.users.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.ee06.wooms.global.util.RegExpUtils.PASSWORD_EXP;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ModifyPasswordInfo {
    @NotBlank(message = "비밀번호는 공백이 될 수 없습니다.")
    String oldPassword;

    @NotBlank(message = "새로운 비밀번호는 공백이 될 수 없습니다.")
//    @Size(min = 8, max = 16, message = "새 비밀번호를 8자 이상 16자 이하로 입력해주세요.")
//    @Pattern(message = "새 비밀번호를 적어도 하나 이상의 영문자, 특수문자, 숫자가 포함되어야 합니다", regexp = PASSWORD_EXP)
    String newPassword;
}
