package com.ee06.wooms.domain.enrollments.entity;

import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import com.ee06.wooms.global.audit.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "wooms_enrollments")
public class Enrollment extends BaseTimeEntity {
    @EmbeddedId
    private Pk pk;

    @MapsId("userUuid")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", columnDefinition = "BINARY(16)")
    private User user;

    @MapsId("woomsId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wooms_id")
    private Wooms wooms;

    private EnrollmentStatus status;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "user_uuid", columnDefinition = "BINARY(16)")
        private UUID userUuid;

        @Column(name = "wooms_id")
        private Long woomsId;
    }

    public static Enrollment of(User user, Wooms wooms, EnrollmentStatus status) {
        return Enrollment.builder()
                .user(user)
                .wooms(wooms)
                .status(status)
                .pk(new Enrollment.Pk(user.getUuid(), wooms.getId()))
                .build();
    }

    public void modifyEnrollmentStatus(EnrollmentStatus status) {
        this.status = status;
    }

}