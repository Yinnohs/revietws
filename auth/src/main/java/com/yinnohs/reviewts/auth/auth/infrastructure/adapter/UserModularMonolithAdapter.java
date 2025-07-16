package com.yinnohs.reviewts.auth.auth.infrastructure.adapter;

import com.yinnohs.reviewts.auth.auth.domain.entities.User;
import com.yinnohs.reviewts.auth.auth.domain.ports.in.UserPort;
import com.yinnohs.reviewts.auth.user.application.dtos.CreateUserRequest;
import com.yinnohs.reviewts.auth.user.application.usecases.CreateUserUseCase;
import com.yinnohs.reviewts.auth.user.application.usecases.ExistByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserModularMonolithAdapter implements UserPort {
    private final CreateUserUseCase userUseCase;
    private final ExistByEmail existsByEmailUseCase;

    @Override
    public Boolean existsByEmail(String email) {
        return existsByEmailUseCase.execute(email);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public String createUser(String firstName, String lastName, String email) {
        var request = new CreateUserRequest(firstName,lastName,email);
        return userUseCase.execute(request);
    }
}
