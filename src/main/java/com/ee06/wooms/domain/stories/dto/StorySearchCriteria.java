package com.ee06.wooms.domain.stories.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StorySearchCriteria {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Nullable
    private String keyword;

    public LocalDateTime getStartDate() {
        return convertToStartOfDay(startDate);
    }

    public LocalDateTime getEndDate() {
        return convertToEndOfDay(endDate);
    }

    public LocalDateTime convertToStartOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

    public LocalDateTime convertToEndOfDay(LocalDate date) {
        return date.atTime(23, 59, 59);
    }
}
