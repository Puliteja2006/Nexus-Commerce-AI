package com.nexuscommerce.service;

import com.nexuscommerce.dto.category.CategoryDto;
import com.nexuscommerce.dto.category.CreateCategoryRequest;
import com.nexuscommerce.dto.category.UpdateCategoryRequest;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategoryTree();

    CategoryDto getCategoryBySlug(String slug);

    CategoryDto getCategoryById(UUID id);

    CategoryDto createCategory(CreateCategoryRequest request);

    CategoryDto updateCategory(UUID id, UpdateCategoryRequest request);

    void deleteCategory(UUID id);
}
