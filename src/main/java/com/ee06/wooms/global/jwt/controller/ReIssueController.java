package com.ee06.wooms.global.jwt.controller;

import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class ReIssueController {
    private final JwtService jwtService;
    @GetMapping("/api/auth/token")
    public ResponseEntity<CommonResponse> issueRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(jwtService.issueRefreshToken(request, response));
    }
}
