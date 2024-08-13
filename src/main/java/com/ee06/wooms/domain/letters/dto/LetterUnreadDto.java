package com.ee06.wooms.domain.letters.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LetterUnreadDto {
    private int totalUnreadCount;
}
