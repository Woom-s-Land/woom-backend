package com.ee06.wooms.global.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AIService {
    private final ChatClient chatClient;
    private final OpenAiAudioSpeechModel speechModel;
    private final OpenAiAudioSpeechOptions speechOptions;

    public String convertScript(String content, String nickname) {
        ChatResponse chatResponse = chatClient.prompt().user( content + "\"사용자 이름: \"" + nickname).call().chatResponse();
        AssistantMessage chatOutput = chatResponse.getResult().getOutput();

        return chatOutput.getContent();
    }

    public InputStream convertMP3File(String script) {
        SpeechPrompt speechPrompt = new SpeechPrompt(script, speechOptions);
        SpeechResponse audioResponse = speechModel.call(speechPrompt);

        byte[] responseAsBytes = audioResponse.getResult().getOutput();

        return new ByteArrayInputStream(responseAsBytes);
    }
}