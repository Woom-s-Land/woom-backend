package com.ee06.wooms.domain.stories.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class StoryResponse {
    private List<StoryDto> stories;
    private String message;
}
