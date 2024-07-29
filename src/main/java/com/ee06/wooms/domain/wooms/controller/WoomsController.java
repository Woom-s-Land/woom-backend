package com.ee06.wooms.domain.wooms.controller;


import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.wooms.dto.WoomsCreateRequestDto;
import com.ee06.wooms.domain.wooms.dto.WoomsDto;
import com.ee06.wooms.domain.wooms.service.WoomsService;
import com.ee06.wooms.global.common.CommonResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class WoomsController {

    private final WoomsService woomsService;

    @PostMapping("/wooms")

    public ResponseEntity<WoomsDto> createWooms(@AuthenticationPrincipal CustomUserDetails currentUser, @RequestBody WoomsCreateRequestDto woomsCreateRequestDto) {
        return ResponseEntity.ok(woomsService.createWooms(currentUser, woomsCreateRequestDto));
    }

    @GetMapping("/wooms")
    public ResponseEntity<List<WoomsDto>> getWoomsInfo(@AuthenticationPrincipal CustomUserDetails currentUser) {
        List<WoomsDto> woomsInfo = woomsService.findAllWoomsByUser(UUID.fromString(currentUser.getUuid()));
        return ResponseEntity.ok(woomsInfo);
    }


    @PostMapping("/wooms/{woomsInviteCode}/users")
    public ResponseEntity<CommonResponse> woomsParticipationRequest(@AuthenticationPrincipal CustomUserDetails currentUser, @PathVariable("woomsInviteCode") String woomsInviteCode) {
        return ResponseEntity.ok(woomsService.createWoomsParticipationRequest(currentUser, woomsInviteCode));

    }
}
