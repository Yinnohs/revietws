package com.yinnohs.reviewts.auth.auth.domain.ports.out;

public interface PasswordService {
    String  hashPassword(String rawPassword);
    boolean compare (String hash, String rawPassword);
}
