package com.yinnohs.reviewts.auth.user.application.usecases;

import com.yinnohs.reviewts.auth.user.application.dtos.CreateUserRequest;
import com.yinnohs.reviewts.auth.user.domain.entities.User;
import com.yinnohs.reviewts.auth.user.domain.ports.out.UserService;
import com.yinnohs.reviewts.auth.user.domain.vo.Email;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserService userService;

    public Long execute (CreateUserRequest request){
        User userToCreate = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(new Email(request.email()))
                .build();

        return  userService.save(userToCreate);
    }
}
