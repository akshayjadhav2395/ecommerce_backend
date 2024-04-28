package com.ecom.payload;

import com.ecom.entity.CartItem;
import com.ecom.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private int cartId;
    private UserDto user;
    private Set<CartItemDto> cartItems = new HashSet<>();


}
