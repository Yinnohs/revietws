package com.yinnohs.reviewts.auth.user.infrastructure.services;

import com.yinnohs.reviewts.auth.user.domain.entities.User;
import com.yinnohs.reviewts.auth.user.domain.ports.out.UserService;
import com.yinnohs.reviewts.auth.user.infrastructure.mappers.UserMapper;
import com.yinnohs.reviewts.auth.user.infrastructure.repositories.UserModelSqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserModelSqlRepository repository;
    private final UserMapper mapper;

    @Override
    public Boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public String save(User user) {
        var model = mapper.domainToModel(user);
        var savedModel = repository.save(model);
        return  savedModel.getId();
    }
}
