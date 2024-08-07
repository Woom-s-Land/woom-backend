package com.ee06.wooms.domain.letters.entity;


import com.ee06.wooms.domain.users.entity.User;
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

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "user_letters")
public class Letter extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_letter_id")
    private Long id;

    @Column(name = "user_letter_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sender_uuid", columnDefinition = "BINARY(16)")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_receiver_uuid", columnDefinition = "BINARY(16)")
    private User receiver;

    @Column(name = "user_letter_status")
    private LetterStatus status;

    @Column(name = "sent_date")
    private LocalDateTime sentDate;

    @Column(name = "receive_date")
    private LocalDateTime receiveDate;

    public void modifyLetterStatusRead(){
        this.status = LetterStatus.READ;
    }

}
