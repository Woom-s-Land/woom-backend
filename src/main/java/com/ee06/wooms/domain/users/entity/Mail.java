package com.ee06.wooms.domain.users.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(timeToLive = 180)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Mail {
    @Id
    private String email;
    private String code;

    @TimeToLive
    private Long expiration;

    public void modifyCode(String code){
        this.code = code;
    }
}
