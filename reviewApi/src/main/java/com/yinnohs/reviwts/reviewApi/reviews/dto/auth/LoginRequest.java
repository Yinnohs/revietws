package com.yinnohs.reviwts.reviewApi.reviews.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
