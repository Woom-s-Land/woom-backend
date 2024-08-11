package com.ee06.wooms.domain.chat.common;

import com.ee06.wooms.domain.chat.ChannelRepository;
import com.ee06.wooms.domain.chat.SessionRepository;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChannelDisconnectListener {
    private final ChannelRepository channelRepository;
    private final SessionRepository sessionRepository;

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
        String sessionId = event.getSessionId();
        UUID woomsId = sessionRepository.get(sessionId);
        channelRepository.get(woomsId).removeWoom(UUID.fromString(userDetails.getUuid()));
        sessionRepository.remove(sessionId);
    }
}


