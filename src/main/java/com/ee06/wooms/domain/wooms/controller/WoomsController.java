package com.ee06.wooms.domain.wooms.controller;


import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.UserInfoDto;
import com.ee06.wooms.domain.wooms.dto.WoomsCreateRequestDto;
import com.ee06.wooms.domain.wooms.dto.WoomsDetailInfoDto;
import com.ee06.wooms.domain.wooms.dto.WoomsDto;
import com.ee06.wooms.domain.wooms.service.WoomsService;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WoomsController {

    private final WoomsService woomsService;

    @PostMapping("/wooms")
    public ResponseEntity<WoomsDto> createWooms(@AuthenticationPrincipal CustomUserDetails currentUser, @RequestBody WoomsCreateRequestDto woomsCreateRequestDto) {
        return ResponseEntity.ok(woomsService.createWooms(currentUser, woomsCreateRequestDto));
    }

    @GetMapping("/wooms")
    public ResponseEntity<List<WoomsDto>> getWoomsInfo( @AuthenticationPrincipal CustomUserDetails currentUser) {
        List<WoomsDto> woomsInfo = woomsService.findAllWooms(UUID.fromString(currentUser.getUuid()));
        return ResponseEntity.ok(woomsInfo);
    }

    @PostMapping("/wooms/{woomsInviteCode}/users")
    public ResponseEntity<CommonResponse> woomsParticipationRequest(@AuthenticationPrincipal CustomUserDetails currentUser,
            @PathVariable("woomsInviteCode") String woomsInviteCode) {
        return ResponseEntity.ok(woomsService.createWoomsParticipationRequest(currentUser, woomsInviteCode));
    }

    @GetMapping("/wooms/{woomsId}/info")
    public ResponseEntity<WoomsDetailInfoDto> getWoomsDetailInfo(@AuthenticationPrincipal CustomUserDetails currentUser,  @PathVariable("woomsId") Long woomsId) {
        return ResponseEntity.ok(woomsService.findWoomsDetail(currentUser, woomsId));
    }

    @GetMapping("/wooms/{woomsId}/enrollment")
    public ResponseEntity<List<UserInfoDto>> getEnrolledUsers(@AuthenticationPrincipal CustomUserDetails currentUser, @PathVariable("woomsId") Long woomsId) {
        return ResponseEntity.ok(woomsService.getEnrolledUsers(currentUser, woomsId));
    }
}