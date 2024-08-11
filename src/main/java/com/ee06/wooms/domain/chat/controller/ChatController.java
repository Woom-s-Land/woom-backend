package com.ee06.wooms.domain.chat.controller;

import com.ee06.wooms.domain.chat.ChannelRepository;
import com.ee06.wooms.domain.chat.SessionRepository;
import com.ee06.wooms.domain.chat.dto.ChatMessage;
import com.ee06.wooms.domain.chat.dto.MoveRequest;
import com.ee06.wooms.domain.chat.dto.MoveResponse;
import com.ee06.wooms.domain.chat.entity.Channel;
import com.ee06.wooms.domain.chat.entity.Woom;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate template;
    private final ChannelRepository channelRepository;
    private final SessionRepository sessionRepository;

    @PostMapping("/join/{woomsId}")
    public void join(@AuthenticationPrincipal CustomUserDetails userDetails,
                     @DestinationVariable("woomsId") UUID woomsId,
                     HttpServletRequest request){
        Woom woom = new Woom(userDetails, woomsId);
        Channel channel = channelRepository.get(woomsId);
        channel.getWooms().forEach(woom1 ->
            {
                try {
                    template.convertAndSend("/ws/send/move/" + woomsId, objectMapper.writeValueAsString(MoveResponse.of(woom1)));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        );
        sessionRepository.put(request.getSession().getId(), woomsId);
        channel.addWoom(woom);
        channelRepository.put(woomsId, channel);
    }

    @MessageMapping("/ws/wooms/chat/{woomsId}")
    public void sendMessage(@AuthenticationPrincipal CustomUserDetails userDetails,
                            @DestinationVariable("woomsId") UUID woomsId,
                            String content) throws Exception {
        ChatMessage chatMessage = ChatMessage.builder()
                .content(content)
                .nickname(userDetails.getNickname())
                .build();
        log.info("chatMessage: {}", chatMessage);
        template.convertAndSend("/ws/send/chat/" + woomsId, objectMapper.writeValueAsString(chatMessage));
    }

    @MessageMapping("/ws/wooms/move/{woomsId}")
    public void move(@AuthenticationPrincipal CustomUserDetails userDetails,
                     @DestinationVariable("woomsId") UUID woomsId,
                     String content) throws Exception {
        MoveRequest moveRequest = objectMapper.readValue(content, MoveRequest.class);
        MoveResponse moveResponse = getMoveResponse(userDetails, moveRequest);

        channelRepository.get(woomsId).moveWoom(new Woom(userDetails, woomsId), moveRequest);

        template.convertAndSend("/ws/send/move/" + woomsId, objectMapper.writeValueAsString(moveResponse));
    }

    public MoveResponse getMoveResponse(CustomUserDetails userDetails, MoveRequest moveRequest) {
        return MoveResponse.builder()
                .x(moveRequest.getX())
                .y(moveRequest.getY())
                .nickName(userDetails.getNickname())
                .costume(Integer.parseInt(userDetails.getCostume()))
                .stepId(moveRequest.getStepId())
                .direction(moveRequest.getDirection())
                .build();
    }

}
