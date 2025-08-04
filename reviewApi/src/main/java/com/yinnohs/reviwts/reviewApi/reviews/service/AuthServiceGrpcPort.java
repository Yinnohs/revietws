package com.yinnohs.reviwts.reviewApi.reviews.service;

import com.yinnohs.reviwts.reviewApi.auth.service.AuthService;
import com.yinnohs.reviwts.reviewApi.reviews.port.AuthServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceGrpcPort implements AuthServicePort {

    private final AuthService authService;

    @Override
    public boolean isValidToken(String token) {
        return authService.isValidToken(token);
    }
}
