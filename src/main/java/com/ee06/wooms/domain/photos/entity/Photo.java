package com.ee06.wooms.domain.photos.entity;

import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.wooms.entity.Wooms;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "wooms_photos")
public class Photo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wooms_photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wooms_id")
    private Wooms wooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", columnDefinition = "BINARY(16)")
    private User user;

    @Column(name = "wooms_photo_file_path")
    private String path;

    @Column(name = "wooms_photo_summary")
    private String summary;

    @Column(name = "wooms_nickname")
    private String nickname;

    @Column(name = "wooms_photo_flipped")
    private WoomsPhotoFlipped flipped;

    @Column(name = "wooms_map_id")
    private Integer mapId;

    public static Photo of(User user, Wooms wooms, String path, String summary, Integer mapId) {
        return Photo.builder()
                .user(user)
                .wooms(wooms)
                .path(path)
                .summary(summary)
                .nickname(user.getNickname())
                .flipped(WoomsPhotoFlipped.UNFLIPPED)
                .mapId(mapId)
                .build();
    }

    public void flip(){
        if(flipped == WoomsPhotoFlipped.FLIPPED) flipped = WoomsPhotoFlipped.UNFLIPPED;
        else flipped = WoomsPhotoFlipped.FLIPPED;
    }
}
