package com.ramondev.ecommerce.mapper;

import com.ramondev.ecommerce.dto.request.UserRequest;
import com.ramondev.ecommerce.dto.response.UserResponse;
import com.ramondev.ecommerce.model.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User toEntity(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .passwordHash(request.password())
                .role(request.role())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
