package com.ramondev.ecommerce.dto.response;

import com.ramondev.ecommerce.model.enums.UserRole;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        UserRole role,
        LocalDateTime createdAt
) {
}
