package com.yinnohs.reviewts.auth.auth.infrastructure.grpc;

import com.yinnohs.reviewts.auth.auth.application.usecases.LoginUseCase;
import com.yinnohs.reviewts.auth.auth.application.usecases.SignUpUseCase;
import com.yinnohs.reviwts.auth.infrastructure.grpc.AuthResponse;
import com.yinnohs.reviwts.auth.infrastructure.grpc.AuthsGrpcServiceGrpc;
import com.yinnohs.reviwts.auth.infrastructure.grpc.LoginRequest;
import com.yinnohs.reviwts.auth.infrastructure.grpc.RegisterRequest;
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
    public void register(RegisterRequest request, StreamObserver<AuthResponse> responseObserver) {
        super.register(request, responseObserver);
    }
}
