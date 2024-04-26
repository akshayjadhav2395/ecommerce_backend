package com.ecom.payload;

import com.ecom.entity.Category;
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
    private CategoryDto category;
}
