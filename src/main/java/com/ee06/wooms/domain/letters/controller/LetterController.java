package com.ee06.wooms.domain.letters.controller;

import com.ee06.wooms.domain.letters.dto.LetterDetailDto;
import com.ee06.wooms.domain.letters.dto.LetterDto;
import com.ee06.wooms.domain.letters.dto.LetterUnreadDto;
import com.ee06.wooms.domain.letters.dto.NewLetterRequest;
import com.ee06.wooms.domain.letters.service.LetterService;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
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

    @Operation(summary = "특정 유저에게 편지를 작성합니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
    })
    @PostMapping("/letters")
    public ResponseEntity<CommonResponse> creatNewLetter(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                         @RequestBody NewLetterRequest newLetterRequest) {
        return ResponseEntity.ok(letterService.createNewLetter(currentUser, newLetterRequest));
    }

    @Operation(summary = "특정 유저의 확인 가능한 모든 편지를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LetterDto.class))),
    })
    @GetMapping("/letters")
    public ResponseEntity<Page<LetterDto>> getLetters(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                      @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(letterService.getSortedLettersBeforeToday(currentUser, pageable));
    }

    @Operation(summary = "특정 유저의 미래에 도착할 편지의 수를 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))),
    })
    @GetMapping("/letters/amount")
    public ResponseEntity<Integer> getFutureLettersAmount(@AuthenticationPrincipal CustomUserDetails currentUser) {
        int amount = letterService.countFutureLetters(currentUser);
        return ResponseEntity.ok(amount);
    }

    @Operation(summary = "특정 유저의 특정 편지를 삭제합니다.")
    @Parameters(value = {
            @Parameter(name = "letterId", description = "해당 Letter의 id", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponse.class))),
    })
    @DeleteMapping("/letters/{letterId}")
    public ResponseEntity<CommonResponse> deleteLetter(@PathVariable("letterId") Long letterId, @AuthenticationPrincipal CustomUserDetails currentUser) {
        return ResponseEntity.ok(letterService.deleteLetter(currentUser, letterId));
    }

    @Operation(summary = "특정 편지의 상세 정보를 확인합니다.")
    @Parameters(value = {
            @Parameter(name = "letterId", description = "해당 Letter의 id", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LetterDetailDto.class))),
    })
    @GetMapping("/letters/{letterId}/detail")
    public ResponseEntity<LetterDetailDto> getLetterDetail(@PathVariable("letterId") Long letterId, @AuthenticationPrincipal CustomUserDetails currentUser) {
        return ResponseEntity.ok(letterService.getLetterDetail(currentUser, letterId));
    }

    @Operation(summary = "읽지 않은 편지의 수를 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LetterDetailDto.class))),
    })
    @GetMapping("/letters/total")
    public ResponseEntity<LetterUnreadDto> getLetterDetail(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return ResponseEntity.ok(letterService.getTotalUnreadLetter(currentUser));
    }
}
