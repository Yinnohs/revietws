package com.yinnohs.reviewts.auth.global.dtos.responses;

public record GenericErrorResponse(
        String message,
        String title,
        long code
) {
}
