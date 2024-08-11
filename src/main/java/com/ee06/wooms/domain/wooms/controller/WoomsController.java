package com.ee06.wooms.domain.wooms.controller;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.UserInfoDto;
import com.ee06.wooms.domain.wooms.dto.WoomsAdminResponse;
import com.ee06.wooms.domain.wooms.dto.WoomsCreateRequestDto;
import com.ee06.wooms.domain.wooms.dto.WoomsDetailInfoDto;
import com.ee06.wooms.domain.wooms.dto.WoomsDto;
import com.ee06.wooms.domain.wooms.dto.WoomsEnrollRequest;
import com.ee06.wooms.domain.wooms.dto.WoomsMandateAdminRequest;
import com.ee06.wooms.domain.wooms.entity.MapColorStatus;
import com.ee06.wooms.domain.wooms.service.WoomsService;
import com.ee06.wooms.global.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @Operation(summary = "새로운 Wooms(그룹)을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WoomsDto.class))),
    })
    @PostMapping("/wooms")
    public ResponseEntity<WoomsDto> createWooms(@AuthenticationPrincipal CustomUserDetails currentUser, @RequestBody WoomsCreateRequestDto woomsCreateRequestDto) {
        return ResponseEntity.ok(woomsService.createWooms(currentUser, woomsCreateRequestDto));
    }

    @Operation(summary = "특정 유저가 속한 모든 Wooms(그룹)을 리스트로 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WoomsDto.class))),
    })
    @GetMapping("/wooms")
    public ResponseEntity<Page<WoomsDto>> getWoomsInfo(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                       @PageableDefault(size = 8) Pageable pageable) {
        return ResponseEntity.ok(woomsService.findAllAcceptedWooms(currentUser, pageable));
    }

    @Operation(summary = "특정 Wooms(그룹)에 참가 요청을 합니다.")
    @Parameters(value = {
            @Parameter(name = "woomsInviteCode", description = "해당 Wooms 초대코드 UUID", example = "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
    })
    @PostMapping("/wooms/{woomsInviteCode}/users")
    public ResponseEntity<CommonResponse> woomsParticipationRequest(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                                    @PathVariable("woomsInviteCode") String woomsInviteCode) {
        return ResponseEntity.ok(woomsService.createWoomsParticipationRequest(currentUser, woomsInviteCode));
    }

    @Operation(summary = "특정 Wooms(그룹)의 모든 정보와 해당 Wooms의 모든 유저 정보를 반환합니다.")
    @Parameters(value = {
            @Parameter(name = "woomsId", description = "해당 Wooms의 ID", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WoomsDetailInfoDto.class))),
    })
    @GetMapping("/wooms/{woomsId}/info")
    public ResponseEntity<WoomsDetailInfoDto> getWoomsDetailInfo(@AuthenticationPrincipal CustomUserDetails currentUser, @PathVariable("woomsId") Long woomsId) {
        return ResponseEntity.ok(woomsService.findWoomsDetail(currentUser, woomsId));
    }

    @Operation(summary = "특정 Wooms의 참가 요청 정보를 리스트로 반환합니다.")
    @Parameters(value = {
            @Parameter(name = "woomsId", description = "해당 Wooms의 ID", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfoDto.class))),
    })
    @GetMapping("/wooms/{woomsId}/enrollment")
    public ResponseEntity<Page<UserInfoDto>> getEnrolledUsers(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                              @PathVariable("woomsId") Long woomsId,
                                                              @PageableDefault(size = 5) Pageable pageable) {

        return ResponseEntity.ok(woomsService.getEnrolledUsers(currentUser, woomsId, pageable));
    }

    @Operation(summary = "Wooms의 참가 요청한 사용자를 거절하거나 승낙합니다.")
    @Parameters(value = {
            @Parameter(name = "woomsId", description = "해당 Wooms의 ID", example = "1"),
            @Parameter(name = "userUuid", description = "타깃 유저의 UUID", example = "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
    })
    @PatchMapping("/wooms/{woomsId}/users/{userUuid}")
    public ResponseEntity<CommonResponse> modifyEnrolledStatus(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                               @PathVariable("woomsId") Long woomsId,
                                                               @PathVariable("userUuid") String userUuid,
                                                               @RequestBody WoomsEnrollRequest updateRequest) {
        return ResponseEntity.ok(woomsService.patchEnrolledUsers(currentUser, woomsId, userUuid, updateRequest));
    }

    @Operation(summary = "관리자가 해당 Wooms의 관리자 권한을 위임합니다.")
    @Parameters(value = {
            @Parameter(name = "woomsId", description = "해당 Wooms의 ID", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
    })
    @PatchMapping("/wooms/{woomsId}/admins/delegations")
    public ResponseEntity<CommonResponse> mandateAdmin(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                       @PathVariable("woomsId") Long woomsId,
                                                       @RequestBody WoomsMandateAdminRequest mandateRequest) {

        return ResponseEntity.ok(woomsService.patchWoomsAdmin(currentUser, woomsId, mandateRequest));
    }

    @Operation(summary = "관리자가 해당 Wooms의 맵색상을 변환합니다.")
    @Parameters(value = {
            @Parameter(name = "woomsId", description = "해당 Wooms의 ID", example = "1"),
            @Parameter(name = "mapColors", description = "해당 Wooms의 ID", example = "RED | GREEN | BLUE"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
    })
    @PatchMapping("/wooms/{woomsId}/colors/{mapColors}")
    public ResponseEntity<CommonResponse> patchWoomsMapColor(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                             @PathVariable("woomsId") Long woomsId,
                                                             @PathVariable("mapColors") MapColorStatus mapColor) {
        return ResponseEntity.ok(woomsService.patchWoomsColor(currentUser, woomsId, mapColor));
    }

    @Operation(summary = "초대코드를 사용해 그룹 이름을 반환합니다.")
    @Parameters(value = {
            @Parameter(name = "woomsInviteCode", description = "해당 Wooms 초대코드 UUID", example = "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
    })
    @GetMapping("/wooms/{woomsInviteCode}/name")
    public ResponseEntity<CommonResponse> woomsGetWoomsName(@PathVariable("woomsInviteCode") String woomsInviteCode) {
        return ResponseEntity.ok(woomsService.getWoomsName(woomsInviteCode));
    }

    @Operation(summary = "그룹에서 탈퇴합니다.")
    @Parameters(value = {
            @Parameter(name = "woomsId", description = "해당 Wooms ID", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
    })
    @DeleteMapping("/wooms/{woomsId}/users")
    public ResponseEntity<CommonResponse> leaveWooms(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                     @PathVariable("woomsId") Long woomsId) {
        return ResponseEntity.ok(woomsService.leaveWooms(currentUser, woomsId));
    }

    @Operation(summary = "특정 그룹 관리자 여부 확인")
    @Parameters(value = {
            @Parameter(name = "woomsId", description = "해당 Wooms ID", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
    })
    @GetMapping("/wooms/{woomsId}/admin")
    public ResponseEntity<WoomsAdminResponse> checkAdmin(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                         @PathVariable("woomsId") Long woomsId) {
        return ResponseEntity.ok(woomsService.checkAdmin(currentUser, woomsId));
    }
}