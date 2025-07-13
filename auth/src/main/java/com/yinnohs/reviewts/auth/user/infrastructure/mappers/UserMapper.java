package com.yinnohs.reviewts.auth.user.infrastructure.mappers;

import com.yinnohs.reviewts.auth.user.domain.entities.User;
import com.yinnohs.reviewts.auth.user.domain.vo.Email;
import com.yinnohs.reviewts.auth.user.infrastructure.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel domainToModel(User user){
        return UserModel.builder()
                .id(user.getId())
                .email(user.getEmail().getValue())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .lastUpdate(user.getLastUpdate())
                .build();
    }

    public  User modelToDomain(UserModel user){
        return User.builder()
                .id(user.getId())
                .email(new Email(user.getEmail()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .lastUpdate(user.getLastUpdate())
                .build();
    }
}
