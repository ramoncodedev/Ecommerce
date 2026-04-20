package com.ramondev.ecommerce.dto.request;

import com.ramondev.ecommerce.model.entity.Category;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        String sku,
        Integer stockQuantity,
        Category category

) {
}
