package com.ee06.wooms.domain.users.controller;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.auth.Join;
import com.ee06.wooms.domain.users.dto.auth.ModifyPasswordInfo;
import com.ee06.wooms.domain.users.dto.auth.UserGameInfo;
import com.ee06.wooms.domain.users.entity.Mail;
import com.ee06.wooms.domain.users.exception.ex.UserNicknameTooLongException;
import com.ee06.wooms.domain.users.exception.ex.UserNotAllowedException;
import com.ee06.wooms.domain.users.service.UserService;
import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.exception.BindingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/authorization/users")
    public ResponseEntity<CommonResponse> authenticationCheck(@AuthenticationPrincipal CustomUserDetails currentUser) {
        if(currentUser == null) throw new UserNotAllowedException();
        return ResponseEntity.ok(new CommonResponse("ok"));
    }

    @PostMapping("/auth/users")
    public ResponseEntity<CommonResponse> join(@Valid @RequestBody Join joinDto, BindingResult result) {
        if (result.hasErrors())
            throw new BindingException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        return ResponseEntity.ok(userService.join(joinDto));
    }

    @PostMapping("/auth/email")
    public ResponseEntity<CommonResponse> sendEmail(@Valid @RequestBody Mail email, BindingResult result) {
        if (result.hasErrors())
            throw new BindingException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        return ResponseEntity.ok(userService.sendEmail(email));
    }

    @PostMapping("/auth/email/code")
    public ResponseEntity<CommonResponse> verifyEmailCode(@Valid @RequestBody Mail email, BindingResult result) {
        if (result.hasErrors())
            throw new BindingException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        return ResponseEntity.ok(userService.verifyEmailCode(email));
    }

    @PatchMapping("/auth/password")
    public ResponseEntity<CommonResponse> reIssuePassword(@RequestBody Mail email, BindingResult result) {
        if (result.hasErrors())
            throw new BindingException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        return ResponseEntity.ok(userService.reIssuePassword(email));
    }

    @GetMapping("/users/info")
    public ResponseEntity<UserGameInfo> userInfo(@AuthenticationPrincipal CustomUserDetails currentUser) {

        return ResponseEntity.ok(userService.userInfo(currentUser));
    }

    @PatchMapping("/users/password")
    public ResponseEntity<CommonResponse> modifyPassword(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                         @RequestBody ModifyPasswordInfo passwordInfo) {
        return ResponseEntity.ok(userService.modifyPassword(currentUser, passwordInfo));
    }

    @PatchMapping("/users/character")
    public ResponseEntity<CommonResponse> modifyUserInfo(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                         @Valid @RequestBody UserGameInfo userGameInfo,
                                                         BindingResult result) {

        if (userGameInfo.getNickname().getBytes(StandardCharsets.UTF_8).length > 21)
            throw new UserNicknameTooLongException();

        if (result.hasErrors())
            throw new BindingException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());

        return ResponseEntity.ok(userService.modifyUserInfo(currentUser, userGameInfo));
    }
}
