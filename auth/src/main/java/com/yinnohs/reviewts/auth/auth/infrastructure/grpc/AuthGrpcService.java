package com.yinnohs.reviewts.auth.auth.infrastructure.grpc;

import com.yinnohs.reviewts.auth.auth.application.dtos.LoginRequest;
import com.yinnohs.reviewts.auth.auth.application.dtos.LoginResponse;
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
    public void login(GrpcLoginRequest request, StreamObserver<AuthResponse> responseObserver) {
        LoginRequest loginRequest = new LoginRequest(
                request.getEmail(),
                request.getPassword()
        );

        LoginResponse loginResponse = loginUseCase.execute(loginRequest);

        AuthResponse response = AuthResponse.newBuilder()
                .setAuthToken(loginResponse.authToken())
                .setRefreshToken(loginResponse.refreshToken())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void register(GrpcSignUpRequest request, StreamObserver<RegisterResponse> responseObserver) {
        SignUpRequest signUpRequest = new SignUpRequest(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );

        String userId = signUpUseCase.execute(signUpRequest);
        RegisterResponse response = RegisterResponse.newBuilder()
                .setId(userId)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
