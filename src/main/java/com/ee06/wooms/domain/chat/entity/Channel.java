package com.ee06.wooms.domain.chat.entity;

import com.ee06.wooms.domain.chat.dto.MoveMessage;

import java.util.HashSet;
import java.util.Set;

public class Channel {
    Set<Woom> wooms;

    public Channel() {
        wooms = new HashSet<>();
    }
    public void addWoom(Woom woom) {
        wooms.add(woom);
    }
    public void removeWoom(Woom woom) {
        wooms.remove(woom);
    }
    public void moveWoom(Woom woom, MoveMessage moveMessage){
        woom.move(moveMessage);
        wooms.add(woom);
    }
    public Set<Woom> getWooms() {
        return wooms;
    }
}
