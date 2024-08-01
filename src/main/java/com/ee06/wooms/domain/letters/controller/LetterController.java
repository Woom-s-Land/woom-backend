package com.ee06.wooms.domain.letters.controller;

import com.ee06.wooms.domain.letters.dto.LetterDto;
import com.ee06.wooms.domain.letters.dto.NewLetterRequest;
import com.ee06.wooms.domain.letters.service.LetterService;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LetterController {

    private final LetterService letterService;

    @PostMapping("/letters")
    public ResponseEntity<CommonResponse> creatNewLetter(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                         @RequestBody NewLetterRequest newLetterRequest) {
        return ResponseEntity.ok(letterService.createNewLetter(currentUser, newLetterRequest));
    }

    @GetMapping("/letters")
    public ResponseEntity<Page<LetterDto>> getLetters(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                      @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(letterService.getSortedLettersBeforeToday(currentUser, pageable));
    }

    @GetMapping("/letters/amount")
    public ResponseEntity<Integer> getFutureLettersAmount(@AuthenticationPrincipal CustomUserDetails currentUser) {
        int amount = letterService.countFutureLetters(currentUser);
        return ResponseEntity.ok(amount);
    }

    @DeleteMapping("/letters/{letterId}")
    public ResponseEntity<CommonResponse> deleteLetter(@PathVariable("letterId") Long letterId, @AuthenticationPrincipal CustomUserDetails currentUser) {
        return ResponseEntity.ok(letterService.deleteLetter(currentUser, letterId));
    }
}
