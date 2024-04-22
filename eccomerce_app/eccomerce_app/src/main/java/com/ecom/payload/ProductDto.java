package com.ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int productId;
    private String productName;
    private String productDesc;
    private double productPrice;
    private boolean live;
    private boolean stock = true;
    private String imageName;
}
