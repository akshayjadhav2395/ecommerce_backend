package com.ecom.controller;

import com.ecom.entity.Product;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.ProductDto;
import com.ecom.payload.UserDto;
import com.ecom.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto)
    {
        ProductDto product = this.productService.saveProduct(productDto);
        return new ResponseEntity<ProductDto>(product, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProduct()
    {
        List<ProductDto> allProduct = this.productService.getAllProducts();
        return new ResponseEntity<List<ProductDto>>(allProduct, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable int productId)
    {
        ProductDto productDto = this.productService.getSingleProduct(productId);
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }


    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable int productId)
    {
        ProductDto productDto1 = this.productService.updateProduct(productDto, productId);
        return new ResponseEntity<ProductDto>(productDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId)
    {
        this.productService.deleteProduct(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully..!", false), HttpStatus.OK);
    }


}
