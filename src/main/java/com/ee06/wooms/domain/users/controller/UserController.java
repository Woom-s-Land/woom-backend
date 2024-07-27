package com.ee06.wooms.domain.users.controller;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.UserGameInfo;
import com.ee06.wooms.domain.users.dto.auth.Join;
import com.ee06.wooms.domain.users.dto.auth.Mail;
import com.ee06.wooms.domain.users.service.UserService;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/auth/users")
    public ResponseEntity<CommonResponse> join(@RequestBody Join joinDto) {
        return ResponseEntity.ok(userService.join(joinDto));
    }

    @PostMapping("/auth/email")
    public ResponseEntity<CommonResponse> sendEmail(@RequestBody Mail email) {
        return ResponseEntity.ok(userService.sendEmail(email));
    }

    @GetMapping("/users/info")
    public ResponseEntity<UserGameInfo> userInfo(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return ResponseEntity.ok(userService.userInfo(currentUser));
    }

    @PatchMapping("/users/password")
    public ResponseEntity<CommonResponse> modifyPassword(@AuthenticationPrincipal CustomUserDetails currentUser, @RequestBody String password) {
        return ResponseEntity.ok(userService.modifyPassword(currentUser, password));
    }

    @PatchMapping("/users/character")
    public ResponseEntity<CommonResponse> modifyUserInfo(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                         @RequestBody UserGameInfo userGameInfo) {
        return ResponseEntity.ok(userService.modifyUserInfo(currentUser, userGameInfo));
    }


}
