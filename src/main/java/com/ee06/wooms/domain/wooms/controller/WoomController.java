package com.ee06.wooms.domain.wooms.controller;


import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.wooms.dto.WoomCreateRequestDto;
import com.ee06.wooms.domain.wooms.dto.WoomDto;
import com.ee06.wooms.domain.wooms.service.WoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WoomController {

    private final WoomService woomService;

    @PostMapping("/groups")
    public ResponseEntity<WoomDto> createWoomGroup(@AuthenticationPrincipal CustomUserDetails currentUser, @RequestBody WoomCreateRequestDto woomCreateRequestDto) {
        return ResponseEntity.ok(woomService.createWoomGroup(currentUser, woomCreateRequestDto));
    }


}
