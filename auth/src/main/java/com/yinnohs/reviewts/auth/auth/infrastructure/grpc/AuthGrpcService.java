package com.yinnohs.reviewts.auth.auth.infrastructure.grpc;

import com.yinnohs.reviewts.auth.auth.application.dtos.LoginRequest;
import com.yinnohs.reviewts.auth.auth.application.dtos.LoginResponse;
import com.yinnohs.reviewts.auth.auth.application.dtos.SignUpRequest;
import com.yinnohs.reviewts.auth.auth.application.usecases.IsValidTokenUseCase;
import com.yinnohs.reviewts.auth.auth.application.usecases.LoginUseCase;
import com.yinnohs.reviewts.auth.auth.application.usecases.SignUpUseCase;
import com.yinnohs.reviwts.auth.infrastructure.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@GrpcService
@Service
@AllArgsConstructor
public class AuthGrpcService extends AuthsGrpcServiceGrpc.AuthsGrpcServiceImplBase {

    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;
    private final IsValidTokenUseCase isValidTokenUseCase;

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

    @Override
    public void isValidToken(IsValidTokenRequest request, StreamObserver<IsValidTokenResponse> responseObserver) {
        String token = request.getAuthToken();
        boolean isValid = isValidTokenUseCase.execute(token);
        IsValidTokenResponse response = IsValidTokenResponse.newBuilder()
                .setIsValid(isValid)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
