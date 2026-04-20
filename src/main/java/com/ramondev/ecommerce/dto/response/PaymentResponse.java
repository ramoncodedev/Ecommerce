package com.ramondev.ecommerce.dto.response;

import com.ramondev.ecommerce.model.enums.PaymentMethod;
import com.ramondev.ecommerce.model.enums.PaymentStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentResponse(
        Long id,
        Long orderId,
        PaymentMethod paymentMethod,
        PaymentStatus status,
        LocalDateTime createdAt
) {
}
