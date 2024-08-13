package com.ee06.wooms.domain.chat.entity;

import com.ee06.wooms.domain.chat.dto.MoveMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Woom {
    private String nickname;
    private UUID woomsId;
    private Integer costume;
    private String x;
    private String y;
    private Integer direction;
    private Integer stepId;

    public Woom(String nickname, Integer costume,  UUID woomsId){
        this.nickname = nickname;
        this.woomsId = woomsId;
        this.costume = costume;
        this.x = "1020";
        this.y = "750";
        this.direction = 0;
    }

    public static Woom DefaultWoom(){
        return Woom.builder()
                .x("1020")
                .y("750")
                .direction(0)
                .stepId(0)
                .build();
    }
    public Woom move(MoveMessage moveMessage){
        this.x = moveMessage.getX();
        this.y = moveMessage.getY();
        this.direction = moveMessage.getDirection();
        this.stepId = moveMessage.getStepId();

        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Woom woom = (Woom) o;
        return Objects.equals(nickname, woom.nickname) && Objects.equals(woomsId, woom.woomsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, woomsId);
    }
}
