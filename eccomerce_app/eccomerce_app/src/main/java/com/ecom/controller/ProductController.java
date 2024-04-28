package com.ecom.controller;

import com.ecom.AppConstants;
import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.payload.UserDto;
import com.ecom.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/categories/{categoryId}/product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto, @PathVariable int categoryId)
    {
        ProductDto product = this.productService.saveProduct(productDto, categoryId);
        return new ResponseEntity<ProductDto>(product, HttpStatus.CREATED);
    }

    @GetMapping("/products/")
    public ResponseEntity<ProductResponse> getAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER_STRING, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_STRING, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING, required = false) String sortDir
    )
    {
        ProductResponse allProduct = this.productService.getAllProducts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<ProductResponse>(allProduct, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable int productId)
    {
        ProductDto productDto = this.productService.getSingleProduct(productId);
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/product")
    public ResponseEntity<ProductResponse> getProductByCategory(
            @PathVariable int categoryId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    )
    {
        ProductResponse productDtos = this.productService.getProductByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<ProductResponse>(productDtos, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable int productId)
    {
        ProductDto productDto1 = this.productService.updateProduct(productDto, productId);
        return new ResponseEntity<ProductDto>(productDto1, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId)
    {
        this.productService.deleteProduct(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully..!", false), HttpStatus.OK);
    }



}
