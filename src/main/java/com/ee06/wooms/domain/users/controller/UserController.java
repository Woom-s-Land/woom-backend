package com.ee06.wooms.domain.users.controller;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.UserInfo;
import com.ee06.wooms.domain.users.dto.auth.Join;
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

    @GetMapping("/auth/users/info")
    public ResponseEntity<UserInfo> userInfo(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return ResponseEntity.ok(userService.userInfo(currentUser));
    }
}
