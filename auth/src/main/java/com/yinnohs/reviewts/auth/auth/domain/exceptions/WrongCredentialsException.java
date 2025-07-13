package com.yinnohs.reviewts.auth.auth.domain.exceptions;

public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException(String message) {
        super(message);
    }
}
