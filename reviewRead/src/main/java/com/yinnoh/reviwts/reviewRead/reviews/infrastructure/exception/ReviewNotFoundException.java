package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
