package com.ee06.wooms.domain.stories.dto;

import lombok.*;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class StoryWriteDto {
    private Long id;
    private UUID userUuid;
    private Long woomsId;
    private String content;
    private String path;
}
