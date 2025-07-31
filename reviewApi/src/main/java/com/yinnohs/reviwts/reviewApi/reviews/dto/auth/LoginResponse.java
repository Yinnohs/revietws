package com.yinnohs.reviwts.reviewApi.reviews.dto.auth;

public record LoginResponse(
        String authToken,
        String refreshToken
) {
}
