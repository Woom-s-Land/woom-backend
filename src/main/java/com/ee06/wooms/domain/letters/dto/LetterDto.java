package com.ee06.wooms.domain.letters.dto;

import com.ee06.wooms.domain.letters.entity.LetterStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LetterDto {
    private Long id;
    private String content;
    private LocalDateTime receiveDate;
    private String senderName;
    private String receiverName;
    private LetterStatus status;
}
