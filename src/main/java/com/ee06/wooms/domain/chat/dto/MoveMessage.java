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
public class MoveMessage {
    private String nickname;
    private String x;
    private String y;
    private Integer direction;
    private Integer costume;
    private Integer stepId;

    public static MoveMessage of(Woom woom){
        return MoveMessage.builder()
                .stepId(woom.getStepId())
                .x(woom.getX())
                .y(woom.getY())
                .direction(woom.getDirection())
                .costume(woom.getCostume())
                .nickname(woom.getNickname())
                .build();
    }
}
