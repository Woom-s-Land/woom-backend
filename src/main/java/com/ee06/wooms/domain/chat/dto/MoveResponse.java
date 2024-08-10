package com.ee06.wooms.domain.chat.dto;

import com.ee06.wooms.domain.chat.entity.Woom;
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
public class MoveResponse {
    private String nickName;
    private Integer x;
    private Integer y;
    private Integer direction;
    private Integer costume;
    private Integer stepId;

    public static MoveResponse of(Woom woom){
        return MoveResponse.builder()
                .stepId(woom.getStepId())
                .x(woom.getX())
                .y(woom.getY())
                .direction(woom.getDirection())
                .costume(woom.getCostume())
                .nickName(woom.getUsername())
                .build();
    }
}
