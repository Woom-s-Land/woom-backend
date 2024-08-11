package com.ee06.wooms.domain.wooms.entity;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;


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

    @Column(name = "map_color_status")
    private MapColorStatus mapColorStatus;

    public static Wooms of(User user, WoomsCreateRequestDto request) {
        return Wooms.builder()
                .user(user)
                .title(request.getWoomsTitle())
                .inviteCode(UUID.randomUUID())
                .mapColorStatus(MapColorStatus.RED)
                .build();
    }

    public WoomsDto toDto() {
        return WoomsDto.builder()
                .woomsId(this.id)
                .woomsInviteCode(this.inviteCode)
                .woomsTitle(this.title)
                .mapColorStatus(this.mapColorStatus)
                .build();
    }

    public WoomsDto toWoomsDto() {
        return WoomsDto.builder()
                .woomsId(this.id)
                .woomsLeaderUuid(this.user.getUuid())
                .woomsInviteCode(this.inviteCode)
                .woomsTitle(this.title)
                .mapColorStatus(this.mapColorStatus)
                .build();
    }

    public void modifyUser(User newUser) {
        this.user = newUser;
    }

    public void modifyMapColorStatus(MapColorStatus mapColor) {
        this.mapColorStatus = mapColor;
    }
}