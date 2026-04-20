package com.ramondev.ecommerce.dto.request;

import com.ramondev.ecommerce.model.enums.UserRole;

public record UserRequest(
        String name,
        String email,
        String password,
        UserRole role
) {
}
