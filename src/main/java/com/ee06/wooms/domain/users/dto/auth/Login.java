package com.ee06.wooms.domain.users.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.ee06.wooms.global.util.RegExpUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Login {
    @NotBlank(message = "이메일은 공백이 될 수 없습니다.")
    @Email(message = "올바른 이메일 형식을 입력해주세요.", regexp = EMAIL_EXP)
    private String email;

    @NotBlank(message = "비밀번호는 공백이 될 수 없습니다.")
//    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
//    @Pattern(message = "비밀번호는 적어도 하나 이상의 영문자, 특수문자, 숫자가 포함되어야 합니다", regexp = PASSWORD_EXP)
    private String password;
}
