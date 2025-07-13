package com.yinnohs.reviewts.auth.auth.domain.ports.in;

import com.yinnohs.reviewts.auth.auth.domain.entities.User;

public interface UserPort {
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    Long createUser(String firstName, String lastName, String email);
}
