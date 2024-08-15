package com.ee06.wooms.global.config;

import com.ee06.wooms.global.util.PromptUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {
    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem(PromptUtils.INIT_PROMPT).build();
    }

    @Bean
    public OpenAiAudioApi openAiAudioApi() {
        return new OpenAiAudioApi(openAiApiKey);
    }

    @Bean
    public OpenAiAudioSpeechModel openAiAudioSpeechModel(OpenAiAudioApi openAiAudioApi) {
        return new OpenAiAudioSpeechModel(openAiAudioApi);
    }

    @Bean
    OpenAiAudioSpeechOptions openAiAudioSpeechOptions() {
        return OpenAiAudioSpeechOptions.builder()
                .withVoice(OpenAiAudioApi.SpeechRequest.Voice.NOVA)
                .withSpeed(1.1f)
                .withResponseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .withModel(OpenAiAudioApi.TtsModel.TTS_1_HD.value)
                .build();
    }
}
