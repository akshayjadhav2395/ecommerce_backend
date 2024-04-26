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
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartItemId;
    @OneToOne
    private Product product;
    private int quantity;
    private double totalProductPrice;
    @ManyToOne
    private Cart cart;

    public double getTotalProductPrice()
    {
        return totalProductPrice;
    }
    public void setTotalProductPrice(double totalProductPrice)
    {
        this.totalProductPrice=this.product.getProductPrice()*this.quantity;
    }
}
