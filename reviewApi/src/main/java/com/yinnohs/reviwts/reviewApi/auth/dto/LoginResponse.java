package com.yinnohs.reviwts.reviewApi.auth.dto;

public record LoginResponse(
        String authToken,
        String refreshToken
) {
}
