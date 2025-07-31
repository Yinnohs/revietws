package com.yinnohs.reviewts.auth.auth.application.usecases;

import com.yinnohs.reviewts.auth.auth.domain.ports.in.TokenPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IsValidTokenUseCase {

    private final TokenPort tokenService;

    public boolean execute(String token) {
        return tokenService.validateToken(token);
    }
}
