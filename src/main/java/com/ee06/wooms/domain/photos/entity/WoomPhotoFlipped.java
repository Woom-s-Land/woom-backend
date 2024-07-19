package com.ee06.wooms.domain.photos.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WoomPhotoFlipped {
    FLIPPED("flipped"),
    UNFLIPPED("unflipped");

    private final String woomPhotoFlipped;

}
