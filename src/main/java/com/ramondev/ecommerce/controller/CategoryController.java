package com.ramondev.ecommerce.controller;


import com.ramondev.ecommerce.dto.request.CategoryResquest;
import com.ramondev.ecommerce.dto.response.CategoryResponse;
import com.ramondev.ecommerce.mapper.CategoryMapper;
import com.ramondev.ecommerce.model.entity.Category;
import com.ramondev.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
     public ResponseEntity<CategoryResponse> createCategory( @RequestBody CategoryResquest resquest){
       Category category = CategoryMapper.toEntity(resquest);
       Category save = categoryService.createCategory(category);

       return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toResponse(save));
   }

   @GetMapping()
   public ResponseEntity<List<CategoryResponse>> findAll(){
        List<Category> categories = categoryService.findAll();
        List<CategoryResponse> responses = categories.stream().map(category -> CategoryMapper.toResponse(category)).toList();

        return ResponseEntity.ok().body(responses);
   }

   @GetMapping("/{id}")
   public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        Category category = categoryService.findById(id);

        return ResponseEntity.ok().body(CategoryMapper.toResponse(category));
   }

   @PutMapping("/{id}")
   public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryResquest resquest){
        Category category = CategoryMapper.toEntity(resquest);
        Category update = categoryService.updateCategory(id, category);

        return ResponseEntity.ok().body(CategoryMapper.toResponse(update));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
   }
}
