package com.yinnohs.reviewts.auth.auth.infrastructure.configs;

import com.yinnohs.reviewts.auth.auth.application.usecases.IsValidTokenUseCase;
import com.yinnohs.reviewts.auth.auth.application.usecases.LoginUseCase;
import com.yinnohs.reviewts.auth.auth.application.usecases.SignUpUseCase;
import com.yinnohs.reviewts.auth.auth.domain.ports.in.TokenPort;
import com.yinnohs.reviewts.auth.auth.domain.ports.in.UserPort;
import com.yinnohs.reviewts.auth.auth.domain.ports.out.AccountService;
import com.yinnohs.reviewts.auth.auth.domain.ports.out.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthBeanConfiguration {
    private final AccountService accountService;
    private final PasswordService passwordService;
    private final UserPort userPort;
    private final TokenPort tokenService;
    @Bean
    public SignUpUseCase signUpUseCase(){
        return new SignUpUseCase(accountService, userPort, passwordService);
    }

    @Bean
    public LoginUseCase loginUseCase(){
        return new LoginUseCase(accountService);
    }

    @Bean
    public IsValidTokenUseCase tokenService() {
        return new IsValidTokenUseCase(tokenService);
    }

}
