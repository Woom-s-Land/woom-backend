package com.ee06.wooms.domain.users.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import static com.ee06.wooms.global.util.RegExpUtils.EMAIL_EXP;

@RedisHash(timeToLive = 180)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Mail {
    @Id
    @NotBlank(message = "이메일은 공백이 될 수 없습니다.")
    @Email(message = "올바른 이메일 형식을 입력해주세요.", regexp = EMAIL_EXP)
    private String email;
    private String code;

    @TimeToLive
    private Long expiration;

    public void modifyCode(String code){
        this.code = code;
    }
}
