package com.ee06.wooms.global.config;

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
}
