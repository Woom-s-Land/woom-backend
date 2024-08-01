package com.ee06.wooms.global.ai.service;

import com.ee06.wooms.global.ai.exception.FailedConvertFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class AIService {
    private final ChatClient chatClient;
    private final OpenAiAudioSpeechModel speechModel;
    private final OpenAiAudioSpeechOptions speechOptions;

    public String convertScript(String content, String nickname) {
        ChatResponse chatResponse = chatClient.prompt().user("사용자 이름: " + nickname + content).call().chatResponse();
        AssistantMessage chatOutput = chatResponse.getResult().getOutput();

        return chatOutput.getContent();
    }

    public File convertMP3File(String script, String fileName) {
        SpeechPrompt speechPrompt = new SpeechPrompt(script, speechOptions);
        SpeechResponse audioResponse = speechModel.call(speechPrompt);

        byte[] responseAsBytes = audioResponse.getResult().getOutput();
        InputStream audioStream = new ByteArrayInputStream(responseAsBytes);

        File mp3File = new File(fileName + ".mp3");
        try {
            Files.copy(audioStream, mp3File.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FailedConvertFileException();
        }

        return mp3File;
    }
}
