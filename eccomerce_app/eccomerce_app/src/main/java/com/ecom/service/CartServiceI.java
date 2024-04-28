package com.ecom.service;

import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;

public interface CartServiceI {

    //add item to cart
    //we will check the availability of cart if cart is available then we will add item to cart otherwise,
    // we'll create new cart and add item to it

    public CartDto addItem(ItemRequest item, String userName);
    public CartDto getCart(String userName);
    public CartDto removeItemFromCart(String userName, int productId);

}
