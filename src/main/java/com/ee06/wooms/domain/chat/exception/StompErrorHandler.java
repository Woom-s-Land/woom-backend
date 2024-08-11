package com.ee06.wooms.domain.chat.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class StompErrorHandler extends StompSubProtocolErrorHandler {

    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex) {
        log.error("error : {}", ex.getMessage());
        if("UNAUTHORIZED".equals(ex.getMessage())){
            return errorMessage("접근권한이 없는 사용자입니다.", StompCommand.ERROR);
        }

        if("INVALID".equals(ex.getMessage())){
            return errorMessage("유효하지 않은 접근입니다.", StompCommand.ERROR);
        }

        return super.handleClientMessageProcessingError(clientMessage, ex);
    }

    private Message<byte[]> errorMessage(String errorMessage, StompCommand command) {
        StompHeaderAccessor accessor = StompHeaderAccessor.create(command);

        return MessageBuilder.createMessage(errorMessage.getBytes(StandardCharsets.UTF_8),
                accessor.getMessageHeaders());
    }
}
