package com.ee06.wooms.domain.letters.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LetterStatus {
    UNREAD("미열람"),
    READ("열람");

    private final String letterStatus;
}
