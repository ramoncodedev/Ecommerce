package com.ramondev.ecommerce.service;

import com.ramondev.ecommerce.model.entity.Category;
import com.ramondev.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }


    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

     public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = findById(id);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setParentCategory(updatedCategory.getParentCategory());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
