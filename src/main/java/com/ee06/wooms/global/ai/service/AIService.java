package com.ee06.wooms.global.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {
    private final ChatClient chatClient;
    public String convertScript(String content, String nickname) {
        ChatResponse chatResponse = chatClient.prompt().user("사용자 이름: " + nickname + content).call().chatResponse();
        AssistantMessage chatOutput = chatResponse.getResult().getOutput();

        return chatOutput.getContent();
    }
}
