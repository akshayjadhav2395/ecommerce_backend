package com.ecom.controller;

import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;
    private String userName = "aks123@gmail.com";

    @PostMapping("/")
    public ResponseEntity<CartDto> addItemToCart(@RequestBody ItemRequest itemRequest)
    {

        CartDto cartDto = this.cartService.addItem(itemRequest, userName);

        return new ResponseEntity<CartDto>(cartDto, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartOfUser()
    {
        CartDto cart = this.cartService.getCart(userName);
        return new ResponseEntity<CartDto>(cart, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<CartDto> removeProductFromCart(@PathVariable int productId)
    {
        CartDto cartDto = this.cartService.removeItemFromCart(userName, productId);
        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
    }
}
