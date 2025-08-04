package com.yinnohs.reviwts.reviewApi.auth.service;

import com.yinnohs.reviwts.auth.infrastructure.grpc.AuthsGrpcServiceGrpc;
import com.yinnohs.reviwts.auth.infrastructure.grpc.GrpcSignUpRequest;
import com.yinnohs.reviwts.reviewApi.auth.dto.LoginRequest;
import com.yinnohs.reviwts.reviewApi.auth.dto.LoginResponse;
import com.yinnohs.reviwts.reviewApi.auth.dto.SignUpRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String signUp(SignUpRequest request){
        var rpcSignUpRequest = GrpcSignUpRequest.newBuilder()
                .setEmail(request.email())
                .setFirstName(request.firstName())
                .setPassword(request.password())
                .setLastName(request.lastName())
                .build();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5251)
                .usePlaintext()
                .build();

        AuthsGrpcServiceGrpc.AuthsGrpcServiceBlockingStub authServiceCaller = AuthsGrpcServiceGrpc.newBlockingStub(channel);

        var response = authServiceCaller.register(rpcSignUpRequest);

        return response.getId();
    }

    public LoginResponse login(LoginRequest request) {
        var rpcLoginRequest = com.yinnohs.reviwts.auth.infrastructure.grpc.GrpcLoginRequest.newBuilder()
                .setEmail(request.email())
                .setPassword(request.password())
                .build();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5251)
                .usePlaintext()
                .build();

        var response = AuthsGrpcServiceGrpc.newBlockingStub(channel).login(rpcLoginRequest);

        return new LoginResponse(
                response.getAuthToken(),
                response.getRefreshToken()
        );
    }

    public boolean isValidToken(String token) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5251)
                .usePlaintext()
                .build();

        var response = AuthsGrpcServiceGrpc.newBlockingStub(channel).isValidToken(
                com.yinnohs.reviwts.auth.infrastructure.grpc.IsValidTokenRequest.newBuilder()
                        .setAuthToken(token)
                        .build()
        );

        return response.getIsValid();
    }
}
