package com.ecom.service.impl;

import com.ecom.dao.CategoryRepository;
import com.ecom.entity.Category;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CategoryDto;
import com.ecom.service.CategoryServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryServiceI {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {

        Category category = this.modelMapper.map(categoryDto, Category.class);

        Category saveCategory = this.categoryRepository.save(category);

        CategoryDto categoryDto1 = this.modelMapper.map(saveCategory, CategoryDto.class);

        return categoryDto1;
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categoryList = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categoryList.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

        return categoryDtos;
    }

    @Override
    public CategoryDto getSingleCategory(int categoryId) {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Resource with given categoryId " + categoryId + "not found on server..!"));

        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);

        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Resource with given categoryId " + categoryId + "not found on server..!"));
        
        category.setTitle(categoryDto.getTitle());

        Category saveCategory = this.categoryRepository.save(category);

        CategoryDto categoryDto1 = this.modelMapper.map(saveCategory, CategoryDto.class);

        return categoryDto1;
    }

    @Override
    public void deleteCategory(int categoryId) {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Resource with given categoryId " + categoryId + "not found on server..!"));

        this.categoryRepository.delete(category);
    }
}
