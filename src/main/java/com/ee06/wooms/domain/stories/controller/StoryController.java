package com.ee06.wooms.domain.stories.controller;

import com.ee06.wooms.domain.stories.dto.StoryResponse;
import com.ee06.wooms.domain.stories.dto.StorySearchCriteria;
import com.ee06.wooms.domain.stories.dto.StoryWriteRequest;
import com.ee06.wooms.domain.stories.service.StoryService;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.global.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    @GetMapping("/wooms/{woomsId}/stories")
    public ResponseEntity<StoryResponse> getStories(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                    @PathVariable Long woomsId,
                                                    @PageableDefault(size = 4, direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(storyService.getStories(currentUser, woomsId, pageable));
    }

    @PostMapping("/wooms/{woomsId}/stories")
    public ResponseEntity<CommonResponse> writeStory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody StoryWriteRequest storyDto,
            @PathVariable Long woomsId
    ) {
        return ResponseEntity.ok(storyService.writeStory(customUserDetails, storyDto, woomsId));
    }

    @GetMapping("/wooms/{woomsId}/stories/date")
    public ResponseEntity<StoryResponse> getSpecificDateStories(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                                @PathVariable Long woomsId,
                                                                @PageableDefault(size = 4, direction = Sort.Direction.DESC, sort = "id") Pageable pageable,
                                                                @RequestBody StorySearchCriteria criteria
                                                                ) {
        return ResponseEntity.ok(storyService.getSpecificDateStories(currentUser, woomsId, pageable, criteria));
    }
}
