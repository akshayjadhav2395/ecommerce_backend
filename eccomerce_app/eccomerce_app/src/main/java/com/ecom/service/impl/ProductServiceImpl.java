package com.ecom.service.impl;

import com.ecom.dao.CategoryRepository;
import com.ecom.dao.ProductRepository;
import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CategoryDto;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.service.ProductServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServiceI {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto saveProduct(ProductDto productDto, int categoryId) {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Product with categoryId " + categoryId + "not found..!"));

        Product product = this.modelMapper.map(productDto, Product.class);
        product.setCategory(category);

        Product savedProduct = this.productRepository.save(product);

        ProductDto dto = this.modelMapper.map(savedProduct, ProductDto.class);

        return dto;
    }

    @Override
    public ProductResponse getAllProducts(int pageNumber, int pageSize) {

        Pageable paegable = PageRequest.of(pageNumber, pageSize);

        Page<Product> page = this.productRepository.findAll(paegable);

        List<Product> productList = page.getContent();

        List<ProductDto> productDtos = productList.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDtos);
        productResponse.setPageNumber(page.getNumber());
        productResponse.setPageSize(page.getSize());
        productResponse.setTotalElement(page.getTotalElements());
        productResponse.setTotalPages(page.getTotalPages());
        productResponse.setLastPage(page.isLast());

        return productResponse;
    }

    @Override
    public ProductDto getSingleProduct(int productId) {

        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Resource with given productId not found..!"));

        ProductDto productDto = this.modelMapper.map(product, ProductDto.class);

        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, int productId) {

        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Resource with given productId not found..!"));

        product.setProductDesc(productDto.getProductDesc());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductName(productDto.getProductName());
        product.setStock(productDto.isStock());

        Product saveProduct = this.productRepository.save(product);

        ProductDto dto = this.modelMapper.map(saveProduct, ProductDto.class);

        return dto;
    }

    @Override
    public void deleteProduct(int productId) {

        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Resource with given productId not found..!"));

        this.productRepository.delete(product);
    }

    @Override
    public ProductResponse getProductByCategory(int categoryId, int pageNumber, int pageSize) {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Product with categoryId " + categoryId + "not found..!"));

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);

        Page<Product> page = this.productRepository.findByCategory(category, pageable);

        List<Product> byCategory = page.getContent();

        List<ProductDto> productDtos = byCategory.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDtos);
        productResponse.setPageNumber(page.getNumber());
        productResponse.setPageSize(page.getSize());
        productResponse.setTotalPages(page.getTotalPages());
        productResponse.setTotalElement(page.getTotalElements());

        return productResponse;
    }
}
