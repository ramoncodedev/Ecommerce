package com.ramondev.ecommerce.dto.request;

import java.math.BigDecimal;

public record OrderItemRequest(
        Long productId,
        Integer quantity,
        BigDecimal unitPrice
) {
}
