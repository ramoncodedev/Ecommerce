package com.ramondev.ecommerce.dto.request;

import com.ramondev.ecommerce.model.entity.Category;
import com.ramondev.ecommerce.model.entity.Product;

import java.util.List;

public record CategoryResquest(
        String name,
        Category parentCategory,
        List<Category> subcategories,
        List<Product> products
) {


}
