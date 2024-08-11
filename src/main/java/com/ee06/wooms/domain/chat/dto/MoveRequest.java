package com.ee06.wooms.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MoveRequest {
    private Integer x;
    private Integer y;
    private Integer direction;
    private Integer stepId;
}
