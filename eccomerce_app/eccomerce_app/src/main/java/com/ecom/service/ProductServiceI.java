package com.ecom.service;

import com.ecom.payload.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductServiceI {

    public ProductDto saveProduct(ProductDto productDto);
    public List<ProductDto> getAllProducts();
    public ProductDto getSingleProduct(int productId);
    public ProductDto updateProduct(ProductDto productDto, int productId);
    public void deleteProduct(int productId);

}
