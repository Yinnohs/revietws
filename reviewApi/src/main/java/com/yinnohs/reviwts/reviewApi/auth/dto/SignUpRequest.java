package com.yinnohs.reviwts.reviewApi.auth.dto;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {

}
