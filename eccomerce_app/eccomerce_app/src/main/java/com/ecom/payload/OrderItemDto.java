package com.ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private int orderItemId;
    private ProductDto product;
    private int quantity;
    private double totalProductPrice;

    public double getTotalProductPrice()
    {
        return totalProductPrice;
    }
    public void setTotalProductPrice()
    {
        this.totalProductPrice=this.product.getProductPrice()*this.quantity;
    }

}
