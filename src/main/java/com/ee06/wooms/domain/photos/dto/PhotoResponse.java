package com.ee06.wooms.domain.photos.dto;

import com.ee06.wooms.domain.photos.entity.WoomsPhotoFlipped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PhotoResponse {
    private Long id;
    private String path;
    private LocalDate date;
    private WoomsPhotoFlipped flipped;
}
