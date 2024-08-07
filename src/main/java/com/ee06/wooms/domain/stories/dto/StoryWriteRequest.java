package com.ee06.wooms.domain.stories.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryWriteRequest {
    private Long id;

    @NotBlank(message = "별명은 공백이 될 수 없습니다.")
    private String userNickname;

    @Size(min = 20, max = 500, message = "사연의 내용은 최소 20자, 최대 500자까지 작성 가능합니다.")
    private String content;

    private Integer costume;

    private String fileName;
}
