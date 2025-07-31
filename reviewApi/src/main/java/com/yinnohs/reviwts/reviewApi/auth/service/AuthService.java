package com.yinnohs.reviwts.reviewApi.auth.service;

import com.yinnohs.reviwts.auth.infrastructure.grpc.AuthsGrpcServiceGrpc;
import com.yinnohs.reviwts.auth.infrastructure.grpc.GrpcSignUpRequest;
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
}
