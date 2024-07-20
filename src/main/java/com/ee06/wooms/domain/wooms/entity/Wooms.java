package com.ee06.wooms.domain.wooms.entity;

import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.global.audit.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "wooms")
public class Wooms extends BaseTimeEntity {

    @Id
    @UuidGenerator(style = Style.TIME)
    @Column(name = "wooms_uuid", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", columnDefinition = "BINARY(16)")
    private User user;

    @Column(name = "wooms_title")
    private String title;

}
