package com.ee06.wooms.domain.stories.entity;

import com.ee06.wooms.domain.stories.dto.StoryWriteRequest;
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
@Table(name = "wooms_stories")
public class Story extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wooms_story_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", columnDefinition = "BINARY(16)")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wooms_id")
    private Wooms wooms;

    @Column(name = "wooms_story_content")
    private String content;

    @Column(name = "wooms_story_file_name")
    private String fileName;

    public static Story of(Wooms wooms, User user, StoryWriteRequest storyWriteRequest, String fileName) {
        return  Story.builder()
                .wooms(wooms)
                .user(user)
                .content(storyWriteRequest.getContent())
                .fileName(fileName)
                .build();
    }
}
