package com.ee06.wooms.domain.wooms.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MapColorStatus {
    RED("RED"),
    GREEN("GREEN"),
    BLUE("BLUE");

    private final String MapColorStatus;
}
