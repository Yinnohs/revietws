package com.yinnohs.reviewts.auth.user.application.dtos;

public record CreateUserRequest(
         String firstName,
         String lastName,
         String email
) {
}
