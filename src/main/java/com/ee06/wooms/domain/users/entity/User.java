package com.ee06.wooms.domain.users.entity;

import com.ee06.wooms.domain.users.dto.UserInfoDto;
import com.ee06.wooms.domain.users.dto.auth.Join;
import com.ee06.wooms.global.audit.BaseTimeEntity;
import com.ee06.wooms.global.util.RandomHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import java.util.UUID;


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

    @Email
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

    public void modifyNickname(String nickname) {
        this.nickname = nickname;
    }

    public void modifyPassword(String password) {
        this.password = password;
    }

    public void modifyCostume(Integer costume) {
        this.costume = costume;
    }

    public static User of(Join joinDto) {
        return User.builder()
                .email(joinDto.getEmail())
                .password(joinDto.getPassword())
                .name(joinDto.getName())
                .status(UserStatus.ACTIVE)
                .costume(RandomHelper.generateCostumeNumber())
                .nickname(RandomHelper.generateNickname())
                .build();
    }

    public UserInfoDto toDto() {
        return UserInfoDto.builder()
                .uuid(this.uuid)
                .name(this.name)
                .nickname(this.nickname)
                .costume(this.costume)
                .build();
    }
}
