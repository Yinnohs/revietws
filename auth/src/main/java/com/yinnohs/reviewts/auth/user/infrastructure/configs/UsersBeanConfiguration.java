package com.yinnohs.reviewts.auth.user.infrastructure.configs;

import com.yinnohs.reviewts.auth.user.application.usecases.CreateUserUseCase;
import com.yinnohs.reviewts.auth.user.application.usecases.ExistByEmail;
import com.yinnohs.reviewts.auth.user.domain.ports.out.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UsersBeanConfiguration {

    private final UserService userService;

    @Bean
    public CreateUserUseCase createUserUseCase(){
        return  new CreateUserUseCase(userService);
    }

    @Bean
    public ExistByEmail existByEmail(){
        return new ExistByEmail(userService);
    }
}
