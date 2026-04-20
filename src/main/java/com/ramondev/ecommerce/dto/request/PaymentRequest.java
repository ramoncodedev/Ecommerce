package com.ramondev.ecommerce.dto.request;

import com.ramondev.ecommerce.model.enums.PaymentMethod;

public record PaymentRequest(
        Long orderId,
        PaymentMethod paymentMethod
) {
}
