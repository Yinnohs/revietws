package com.yinnohs.reviwts.reviewApi.reviews.controller;

import com.yinnohs.reviwts.reviewApi.reviews.dto.auth.LoginRequest;
import com.yinnohs.reviwts.reviewApi.reviews.dto.auth.SignUpRequest;
import com.yinnohs.reviwts.reviewApi.reviews.service.auth.AuthService;
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
    public ResponseEntity<?> Login(LoginRequest request) {
        return ResponseEntity.ok(" not implemented yet");
    }
}
