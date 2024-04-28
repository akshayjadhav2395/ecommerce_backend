package com.ecom.payload;

import com.ecom.entity.Cart;
import com.ecom.entity.Product;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private int cartItemId;
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
