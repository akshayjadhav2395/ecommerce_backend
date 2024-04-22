package com.ecom.dao;

import com.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Product findByProductId(int productId);
    public List<Product> findByProductName(String productName);
}
