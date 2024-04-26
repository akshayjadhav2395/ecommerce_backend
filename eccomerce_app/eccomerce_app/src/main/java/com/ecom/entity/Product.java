package com.ecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    @Column(name="product_brand_name", length=300, unique=true)
    private String productName;
    private String productDesc;
    private double productPrice;
    private boolean isLive;
    private boolean stock = true;
    private String imageName;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
