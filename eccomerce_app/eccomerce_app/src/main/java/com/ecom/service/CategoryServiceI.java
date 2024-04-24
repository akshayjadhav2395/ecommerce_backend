package com.ecom.service;

import com.ecom.payload.CategoryDto;
import com.ecom.payload.ProductDto;

import java.util.List;

public interface CategoryServiceI {

    public CategoryDto saveCategory(CategoryDto categoryDto);
    public List<CategoryDto> getAllCategories();
    public CategoryDto getSingleCategory(int categoryId);
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
    public void deleteCategory(int categoryId);

}
