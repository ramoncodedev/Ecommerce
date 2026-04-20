package com.ramondev.ecommerce.mapper;

import com.ramondev.ecommerce.dto.request.CategoryResquest;
import com.ramondev.ecommerce.dto.response.CategoryResponse;
import com.ramondev.ecommerce.model.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public Category toEntity(CategoryResquest categoryResquest) {
        return Category.builder()
                .name(categoryResquest.name())
                .parentCategory(categoryResquest.parentCategory())
                .subcategories(categoryResquest.subcategories())
                .products(categoryResquest.products())
                .build();
    }

    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategory(category.getParentCategory())
                .subcategories(category.getSubcategories())
                .products(category.getProducts())
                .build();
    }
}