package com.ramondev.ecommerce.dto.response;


import com.ramondev.ecommerce.model.entity.Category;
import com.ramondev.ecommerce.model.entity.Product;
import lombok.Builder;

import java.util.List;

@Builder
public record CategoryResponse(

        Long id,
        String name,
        Category parentCategory,
        List<Category> subcategories,
        List<Product> products
) {
}
