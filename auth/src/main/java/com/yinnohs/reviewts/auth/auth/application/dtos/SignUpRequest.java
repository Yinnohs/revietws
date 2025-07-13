package com.yinnohs.reviewts.auth.auth.application.dtos;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {

}
