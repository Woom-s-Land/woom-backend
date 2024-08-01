package com.ee06.wooms.domain.stories.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class StoryDto {
    private Long id;
    private String userNickname;
    private String content;
    private String path;
}
