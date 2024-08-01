package com.ee06.wooms.domain.letters.controller;

import com.ee06.wooms.domain.letters.dto.NewLetterRequest;
import com.ee06.wooms.domain.letters.service.LetterService;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.auth.ModifyPasswordInfo;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
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




}
