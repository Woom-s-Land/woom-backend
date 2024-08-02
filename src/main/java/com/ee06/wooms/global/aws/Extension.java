package com.ee06.wooms.global.aws;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Extension {
    STORY(".mp3");

    private final String extension;
}
