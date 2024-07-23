package com.ee06.wooms.domain.users.entity;

import com.ee06.wooms.global.audit.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @UuidGenerator(style = Style.TIME)
    @Column(name = "user_uuid", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    private String name;

    @Column(name = "social_provider")
    private SocialProvider socialProvider;

    @Column(name = "user_status")
    private UserStatus status;

    @Column(name = "user_costume")
    private Integer costume;

    @Column(name = "user_nickname")
    private String nickname;

}
