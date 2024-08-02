package com.ee06.wooms.domain.stories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Script {
    NOT_FOUND_STORIES("해당하는 사연이 없습니다.");
    private final String script;
}
