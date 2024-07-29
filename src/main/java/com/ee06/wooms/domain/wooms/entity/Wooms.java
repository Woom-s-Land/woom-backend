package com.ee06.wooms.domain.wooms.entity;

import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.wooms.dto.WoomsCreateRequestDto;
import com.ee06.wooms.domain.wooms.dto.WoomsDto;
import com.ee06.wooms.global.audit.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "wooms")
public class Wooms extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wooms_id")
    private Long id;

    @Column(name = "invite_code", columnDefinition = "BINARY(16)")
    private UUID inviteCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", columnDefinition = "BINARY(16)")
    private User user;

    @Column(name = "wooms_title")
    private String title;

    public static Wooms of(User user, WoomsCreateRequestDto request) {
        return Wooms.builder()
                .user(user)
                .title(request.getWoomsTitle())
                .inviteCode(UUID.randomUUID())
                .build();
    }

    public WoomsDto toDto() {
        return WoomsDto.builder()
                .woomsId(this.id)
                .woomsInviteCode(this.inviteCode)
                .woomsTitle(this.title)
                .build();
    }

}