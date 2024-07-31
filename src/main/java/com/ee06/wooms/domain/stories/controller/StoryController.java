package com.ee06.wooms.domain.stories.controller;

import com.ee06.wooms.domain.stories.dto.StoryWriteDto;
import com.ee06.wooms.domain.stories.service.StoryService;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wooms")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    @PostMapping("/{woomsId}/stories")
    public ResponseEntity<CommonResponse> writeStory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody StoryWriteDto storyDto,
            @PathVariable Long woomsId
    ) {
        return ResponseEntity.ok(storyService.writeStory(customUserDetails, storyDto, woomsId));
    }
}
