package com.ee06.wooms.domain.chat;

import com.ee06.wooms.domain.chat.entity.Channel;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class ChannelRepository {
    private ConcurrentMap<UUID, Channel> channelCache = new ConcurrentHashMap<>();

    public void put(UUID key, Channel value) {
        channelCache.put(key, value);
    }

    public Channel get(UUID key) {
        return channelCache.getOrDefault(key, new Channel());
    }
    public void remove(UUID key) { this.channelCache.remove(key); }

    public int size() {
        return this.channelCache.size();
    }
}
