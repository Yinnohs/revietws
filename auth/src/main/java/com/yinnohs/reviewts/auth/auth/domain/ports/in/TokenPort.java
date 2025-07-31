package com.yinnohs.reviewts.auth.auth.domain.ports.in;


import com.yinnohs.reviewts.auth.auth.domain.entities.Account;

public interface TokenPort {
     String extractAccountEmail(String token);
     boolean validateToken(String token);
}
