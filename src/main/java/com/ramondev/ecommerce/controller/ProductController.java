package com.ramondev.ecommerce.controller;


import com.ramondev.ecommerce.dto.request.ProductRequest;
import com.ramondev.ecommerce.dto.response.ProductResponse;
import com.ramondev.ecommerce.mapper.ProductMapper;
import com.ramondev.ecommerce.model.entity.Product;
import com.ramondev.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct( @RequestBody ProductRequest request) {

        Product product = ProductMapper.toEntity(request);
        Product ProductSave = productService.createProduct(product);

        return ResponseEntity.ok(ProductMapper.toResponse(ProductSave));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<Product> findProduct = productService.findAll();
        List<ProductResponse> response = findProduct.stream()
                .map(ProductMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById( @PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(ProductMapper.toResponse(product));
    }

    @PutMapping("/{id}")
     public ResponseEntity<ProductResponse> updateProduct( @PathVariable Long id, ProductRequest request) {
        Product product = ProductMapper.toEntity(request);
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(ProductMapper.toResponse(updatedProduct));
    }

    @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteProduct( @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
