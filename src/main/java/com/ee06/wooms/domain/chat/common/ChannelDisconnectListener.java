package com.ee06.wooms.domain.chat.common;

import com.ee06.wooms.domain.chat.ChannelRepository;
import com.ee06.wooms.domain.chat.SessionRepository;
import com.ee06.wooms.domain.chat.entity.Woom;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class ChannelDisconnectListener {
    private final ChannelRepository channelRepository;
    private final SessionRepository sessionRepository;

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        Woom woom = sessionRepository.get(sessionId);
        channelRepository.get(woom.getWoomsId()).removeWoom(woom);
        sessionRepository.remove(sessionId);
    }
}


