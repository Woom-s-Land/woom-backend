package com.ee06.wooms.domain.comments.entity;

import com.ee06.wooms.domain.comments.dto.CommentRequest;
import com.ee06.wooms.domain.comments.dto.CommentResponse;
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
@Table(name = "wooms_comments")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wooms_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wooms_id")
    private Wooms wooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", columnDefinition = "BINARY(16)")
    private User user;

    @Column(name = "wooms_comment_content")
    private String content;

    public static Comment of(Wooms wooms, User user, CommentRequest commentRequest) {
        return  Comment.builder()
                .wooms(wooms)
                .user(user)
                .content(commentRequest.getContent())
                .build();
    }
    public CommentResponse toDto(){
        return CommentResponse.builder()
                .nickname(user.getNickname())
                .content(content)
                .costume(user.getCostume())
                .createdDate(getCreatedDate())
                .build();
    }
}
