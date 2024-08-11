package com.ee06.wooms.domain.comments.controller;

import com.ee06.wooms.domain.comments.dto.CommentRequest;
import com.ee06.wooms.domain.comments.dto.CommentResponse;
import com.ee06.wooms.domain.comments.exception.ex.ExistWroteCommentException;
import com.ee06.wooms.domain.comments.service.CommentService;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.global.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments/wooms")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{woomsId}")
    public ResponseEntity<CommonResponse> createComment(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                        @PathVariable("woomsId") Long woomsId,
                                                        @Valid @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(commentService.create(userDetails, woomsId, commentRequest));
    }

    @GetMapping("/{woomsId}")
    public ResponseEntity<List<CommentResponse>> getComment(@PathVariable("woomsId") Long woomsId,
                                                            @PageableDefault(size = 4, direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(commentService.getComments(woomsId, pageable));
    }

    @GetMapping("/{woomsId}/today")
    public ResponseEntity<CommonResponse> isWroteToday(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                       @PathVariable("woomsId") Long woomsId) {
        if(commentService.isWroteToday(userDetails, woomsId)) throw new ExistWroteCommentException();
        return ResponseEntity.ok(new CommonResponse("ok"));
    }
}
