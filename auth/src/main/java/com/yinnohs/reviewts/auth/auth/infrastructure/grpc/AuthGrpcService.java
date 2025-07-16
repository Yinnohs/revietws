package com.yinnohs.reviewts.auth.auth.infrastructure.grpc;

import com.yinnohs.reviewts.auth.auth.application.dtos.SignUpRequest;
import com.yinnohs.reviewts.auth.auth.application.usecases.LoginUseCase;
import com.yinnohs.reviewts.auth.auth.application.usecases.SignUpUseCase;
import com.yinnohs.reviwts.auth.infrastructure.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class AuthGrpcService extends AuthsGrpcServiceGrpc.AuthsGrpcServiceImplBase {

    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;

    @Override
    public void login(LoginRequest request, StreamObserver<AuthResponse> responseObserver) {
        super.login(request, responseObserver);
    }

    @Override
    public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
        SignUpRequest signUpRequest = new SignUpRequest(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );

        Long userId = signUpUseCase.execute(signUpRequest);

        RegisterResponse response = RegisterResponse.newBuilder()
                .setId(userId)
                .build();
    }
}
