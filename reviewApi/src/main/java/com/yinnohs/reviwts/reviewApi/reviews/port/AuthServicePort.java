package com.yinnohs.reviwts.reviewApi.reviews.port;

public interface AuthServicePort {
    boolean isValidToken(String token);
}
