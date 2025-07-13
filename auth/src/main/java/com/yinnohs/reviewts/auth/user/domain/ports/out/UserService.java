package com.yinnohs.reviewts.auth.user.domain.ports.out;

import com.yinnohs.reviewts.auth.user.domain.entities.User;

public interface UserService {
     /*List<User> findAll();
     Optional<User> findById(Long userId);
     Optional<User> findByEmail(String email);*/
     Boolean existByEmail(String email);
     Long save(User user);
}
