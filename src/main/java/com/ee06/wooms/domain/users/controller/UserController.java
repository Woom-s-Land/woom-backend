package com.ee06.wooms.domain.users.controller;

import com.ee06.wooms.domain.users.dto.auth.Join;
import com.ee06.wooms.domain.users.service.UserService;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/auth/users")
    public ResponseEntity<CommonResponse> join(@RequestBody Join joinDto) {
        return ResponseEntity.ok(userService.join(joinDto));
    }

}
