package com.ramondev.ecommerce.mapper;

import com.ramondev.ecommerce.dto.request.OrderItemRequest;
import com.ramondev.ecommerce.dto.request.OrderRequest;
import com.ramondev.ecommerce.dto.response.OrderItemResponse;
import com.ramondev.ecommerce.dto.response.OrderResponse;
import com.ramondev.ecommerce.model.entity.Order;
import com.ramondev.ecommerce.model.entity.OrderItem;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class OrderMapper {

    public OrderItem toItemEntity(OrderItemRequest itemRequest) {
        OrderItem item = new OrderItem();
        item.setQuantity(itemRequest.quantity());
        item.setUnitPrice(itemRequest.unitPrice());
        return item;
    }

    public OrderItemResponse toItemResponse(OrderItem item) {
        return new OrderItemResponse(
                item.getId(),
                item.getProduct() != null ? item.getProduct().getId() : null,
                item.getProduct() != null ? item.getProduct().getName() : null,
                item.getQuantity(),
                item.getUnitPrice()
        );
    }

    public OrderResponse toResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(OrderMapper::toItemResponse)
                .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser() != null ? order.getUser().getId() : null)
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .items(itemResponses)
                .createdAt(order.getCreatedAt())
                .build();
    }
}
