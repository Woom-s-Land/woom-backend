package com.ee06.wooms.domain.stories.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryResponse {
    private List<StoryWriteRequest> stories;
    private String message;
    private Integer totalPage;

}
