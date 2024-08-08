package com.ee06.wooms.global.ai.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TTSRequest {
    private final String script;
    private final RequestCallback callback;
}
