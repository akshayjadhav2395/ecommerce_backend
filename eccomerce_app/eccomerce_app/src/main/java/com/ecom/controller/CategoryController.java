package com.ecom.controller;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.CategoryDto;
import com.ecom.payload.ProductDto;
import com.ecom.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> saveProduct(@RequestBody CategoryDto categoryDto)
    {
        CategoryDto category = this.categoryService.saveCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory()
    {
        List<CategoryDto> categories = this.categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int categoryId)
    {
        CategoryDto categoryDto = this.categoryService.getSingleCategory(categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }


    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateProduct(@RequestBody CategoryDto categoryDto, @PathVariable int categoryId)
    {
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully..!", false), HttpStatus.OK);
    }

}
