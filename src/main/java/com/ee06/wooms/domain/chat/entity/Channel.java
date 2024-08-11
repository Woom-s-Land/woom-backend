package com.ee06.wooms.domain.chat.entity;

import com.ee06.wooms.domain.chat.dto.MoveRequest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Channel {
    Set<Woom> wooms;

    public Channel() {
        wooms = new HashSet<>();
    }
    public void addWoom(Woom woom) {
        wooms.add(woom);
    }
    public void removeWoom(UUID uuid) {
        wooms.remove(Woom.deleteWoom(uuid));
    }
    public void moveWoom(Woom woom, MoveRequest moveRequest){
        woom.move(moveRequest);
        wooms.add(woom);
    }
    public Set<Woom> getWooms() {
        return wooms;
    }
}
