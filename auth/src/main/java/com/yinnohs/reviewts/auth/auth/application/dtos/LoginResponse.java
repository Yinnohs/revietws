package com.yinnohs.reviewts.auth.auth.application.dtos;

public record LoginResponse(
        String authToken,
        String refreshToken
) {
}
