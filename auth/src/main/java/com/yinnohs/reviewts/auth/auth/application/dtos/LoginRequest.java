package com.yinnohs.reviewts.auth.auth.application.dtos;

public record LoginRequest(
        String email,
        String password
) {
}
