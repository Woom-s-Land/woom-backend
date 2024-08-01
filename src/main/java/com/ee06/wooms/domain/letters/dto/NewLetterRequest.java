package com.ee06.wooms.domain.letters.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewLetterRequest {

    private String targetUserUuid;

    @Size(max = 500)
    private String content;

    private LocalDateTime receiveDateTime;
}
