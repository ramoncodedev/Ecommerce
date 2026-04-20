package com.ramondev.ecommerce.dto.request;

import com.ramondev.ecommerce.model.enums.OrderStatus;

import java.util.List;

public record OrderRequest(
        Long userId,
        OrderStatus status,
        List<OrderItemRequest> items
) {
}
