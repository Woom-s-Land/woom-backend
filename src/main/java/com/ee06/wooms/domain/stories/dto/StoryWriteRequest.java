package com.ee06.wooms.domain.stories.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryWriteRequest {
    private Long id;
    private String userNickname;
    private String content;
    private String fileName;
}
