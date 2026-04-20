package com.ramondev.ecommerce.mapper;

import com.ramondev.ecommerce.dto.request.ProductRequest;
import com.ramondev.ecommerce.dto.response.ProductResponse;
import com.ramondev.ecommerce.model.entity.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public Product toEntity(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .sku(productRequest.sku())
                .price(productRequest.price())
                .stockQuantity(productRequest.stockQuantity())
                .build();
    }


    public ProductResponse toResponse(Product product){
       return ProductResponse.builder()
               .id(product.getId())
               .name(product.getName())
               .sku(product.getSku())
               .description(product.getDescription())
               .price(product.getPrice())
               .stockQuantity(product.getStockQuantity())
               .createdAt(product.getCreatedAt())
               .build();
    }
}
