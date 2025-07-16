package com.yinnohs.reviewts.auth.user.domain.ports.out;

import com.yinnohs.reviewts.auth.user.domain.entities.User;

public interface UserService {
     Boolean existByEmail(String email);
     String save(User user);
}
