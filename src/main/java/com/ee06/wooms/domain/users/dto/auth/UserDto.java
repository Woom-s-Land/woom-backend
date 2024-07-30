package com.ee06.wooms.domain.users.dto.auth;

import com.ee06.wooms.domain.users.entity.SocialProvider;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.entity.UserStatus;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private UUID uuid;
    private String email;
    private String password;
    private String name;
    private SocialProvider socialProvider;
    private UserStatus status;
    private Integer costume;
    private String nickname;
}
