package com.ee06.wooms.domain.chat;

import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class SessionRepository {
    private ConcurrentMap<String, UUID> sessionCache = new ConcurrentHashMap<>();

    public void put(String key, UUID value) {
        sessionCache.put(key, value);
    }

    public UUID get(String key) {
        return sessionCache.getOrDefault(key, UUID.randomUUID());
    }

    public void remove(String key) { this.sessionCache.remove(key); }

    public int size() {
        return this.sessionCache.size();
    }
}
