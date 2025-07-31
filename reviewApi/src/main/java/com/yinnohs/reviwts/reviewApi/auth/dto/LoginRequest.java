package com.yinnohs.reviwts.reviewApi.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
