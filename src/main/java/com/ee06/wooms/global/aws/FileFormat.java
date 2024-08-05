package com.ee06.wooms.global.aws;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileFormat {
    STORY_EXTENSION(".mp3"),
    AUDIO_TYPE("audio/mpeg");

    private final String format;
}
