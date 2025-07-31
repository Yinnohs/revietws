package com.yinnohs.reviwts.reviewApi.auth.controller;

import com.yinnohs.reviwts.reviewApi.auth.dto.LoginRequest;
import com.yinnohs.reviwts.reviewApi.auth.dto.SignUpRequest;
import com.yinnohs.reviwts.reviewApi.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        String userId = authService.signUp(request);
        return ResponseEntity.ok(userId);
    }

    @PostMapping
    public ResponseEntity<?> Login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
