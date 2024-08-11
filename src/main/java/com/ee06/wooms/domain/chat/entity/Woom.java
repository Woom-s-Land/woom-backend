package com.ee06.wooms.domain.chat.entity;

import com.ee06.wooms.domain.chat.dto.MoveRequest;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
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
@NoArgsConstructor
@AllArgsConstructor
public class Woom {
    private UUID id;
    private String username;
    private UUID woomsId;
    private Integer costume;
    private Integer x;
    private Integer y;
    private Integer direction;
    private Integer stepId;

    public Woom(CustomUserDetails userDetails, UUID woomsId){
        this.id = UUID.fromString(userDetails.getUuid());
        this.username = userDetails.getUsername();
        this.woomsId = woomsId;
        this.costume = Integer.valueOf(userDetails.getCostume());
        this.x = 1020;
        this.y = 750;
        this.direction = 0;
    }

    public Woom move(MoveRequest moveRequest){
        this.x = moveRequest.getX();
        this.y = moveRequest.getY();
        this.direction = moveRequest.getDirection();
        this.stepId = moveRequest.getStepId();

        return this;
    }

    public static Woom deleteWoom(UUID id){
        return Woom.builder().id(id).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Woom woom = (Woom) o;
        return Objects.equals(id, woom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
