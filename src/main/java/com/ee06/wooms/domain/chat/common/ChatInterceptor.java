package com.ee06.wooms.domain.chat.common;//package com.ee06.wooms.domain.chat.common;
//
//import com.ee06.wooms.domain.chat.SessionRepository;
//import com.ee06.wooms.domain.chat.exception.StompError;
//import com.ee06.wooms.global.jwt.JWTUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageDeliveryException;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class ChatInterceptor implements ChannelInterceptor {
//
//    private final JWTUtil jwtUtil;
//    private final SessionRepository sessionRepository;
//
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        StompCommand command = accessor.getCommand();
//        if(StompCommand.CONNECT == command) {
//            String token = accessor.getFirstNativeHeader("Authorization");
//            String cookie = accessor.getFirstNativeHeader("Set-Cookie");
//
//            if (!jwtUtil.validateToken(token)) {
//                throw new MessageDeliveryException(StompError.UNAUTHORIZED.name());
//            }
//            sessionRepository.put(accessor.getSessionId(), UUID.fromString(jwtUtil.getChannelUuid(token)));
//        }
//        return message;
//    }
//}