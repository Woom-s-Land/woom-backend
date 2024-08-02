package com.ee06.wooms.domain.stories.controller;

import com.ee06.wooms.domain.stories.dto.StoryWriteRequest;
import com.ee06.wooms.domain.stories.dto.StoryResponse;
import com.ee06.wooms.domain.stories.service.StoryService;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class StoryController {
    private final StoryService storyService;

    @GetMapping("/wooms/{woomsId}/stories")
    public ResponseEntity<StoryResponse> getStories(@PathVariable Long woomsId,
                                                    @PageableDefault(size = 4) Pageable pageable) {
        return ResponseEntity.ok(storyService.getStories(woomsId, pageable));
    }

    @PostMapping("/wooms/{woomsId}/stories")
    public ResponseEntity<CommonResponse> writeStory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody StoryWriteRequest storyDto,
            @PathVariable Long woomsId
    ) {
        return ResponseEntity.ok(storyService.writeStory(customUserDetails, storyDto, woomsId));
    }
}
