package com.ecom.service.impl;

import com.ecom.dao.ProductRepository;
import com.ecom.entity.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.ProductDto;
import com.ecom.service.ProductServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServiceI {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {

        Product product = this.modelMapper.map(productDto, Product.class);

        Product savedProduct = this.productRepository.save(product);

        ProductDto dto = this.modelMapper.map(savedProduct, ProductDto.class);

        return dto;
    }

    @Override
    public List<ProductDto> getAllProducts() {

        List<Product> productList = this.productRepository.findAll();

        List<ProductDto> productDtos = productList.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtos;
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
}
