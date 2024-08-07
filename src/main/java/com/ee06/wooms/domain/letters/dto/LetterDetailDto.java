package com.ee06.wooms.domain.letters.dto;

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
public class LetterDetailDto {
    private Long id;
    private LocalDateTime sentDate;
    private String senderName;
    private String content;
}
