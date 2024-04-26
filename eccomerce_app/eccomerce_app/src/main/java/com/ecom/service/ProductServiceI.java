package com.ecom.service;

import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductServiceI {

    public ProductDto saveProduct(ProductDto productDto, int categoryId);
    public ProductResponse getAllProducts(int pageNumber, int pageSize);
    public ProductDto getSingleProduct(int productId);
    public ProductDto updateProduct(ProductDto productDto, int productId);
    public void deleteProduct(int productId);
    public ProductResponse getProductByCategory(int categoryId, int pageNumber, int pageSize);

}
