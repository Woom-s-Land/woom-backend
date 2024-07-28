package com.ee06.wooms.domain.users.dto.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Join {
    private String email;
    private String password;
    private String name;
}