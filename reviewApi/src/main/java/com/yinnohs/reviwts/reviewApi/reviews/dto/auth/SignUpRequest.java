package com.yinnohs.reviwts.reviewApi.reviews.dto.auth;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {

}
