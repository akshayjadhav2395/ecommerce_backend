package com.ecom.service.impl;

import com.ecom.dao.CartRepository;
import com.ecom.dao.ProductRepository;
import com.ecom.dao.UserRepository;
import com.ecom.entity.Cart;
import com.ecom.entity.CartItem;
import com.ecom.entity.Product;
import com.ecom.entity.User;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.service.CartServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartServiceI {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDto addItem(ItemRequest item, String userName) {

        int productId = item.getProductId();
        int quantity = item.getQuantity();

        if(quantity<=0)
        {
            throw new ResourceNotFoundException("Invalid Quantity..!");
        }

        //get user from userName
        User user = this.userRepository.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException("User with given username " + userName + "not found...!"));

        //get the product from DB
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with given productId " + productId + "not found..!"));
        if(!product.isStock())
        {
            throw new ResourceNotFoundException("Product is out of stock..!");
        }
        System.out.println(product.getProductPrice());

        //create new cartItem with product and quantity
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalProductPrice();

        //getting cart from user
        Cart cart = user.getCart();

        //if cart is null means user doesn't have cart
        if(cart==null)
        {
            //we will create new cart
             cart = new Cart();
            cart.setUser(user);
        }

        Set<CartItem> cartItems = cart.getCartItems();

        AtomicReference<Boolean> flag = new AtomicReference<>(false);

        //check
        Set<CartItem> newItems = cartItems.stream().map((i) -> {
            //changes
            if(i.getProduct().getProductId()==product.getProductId())
            {
                i.setQuantity(quantity);
                i.setTotalProductPrice();
                flag.set(true);
            }

            return i;
        }).collect(Collectors.toSet());

        //check
        if(flag.get())
        {
            //setting newItems in place of items
            cartItems.clear();
            cartItems.addAll(newItems);
        }
        else {
            //add items into cart
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        }

        Cart updatedCart = this.cartRepository.save(cart);

        CartDto cartDto = this.modelMapper.map(updatedCart, CartDto.class);

        return cartDto;
    }

    @Override
    public CartDto getCart(String userName) {

        User user = this.userRepository.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException("User with given userName " + userName + "not found..!"));

        Cart cart = this.cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart with given userName not found..!"));

        CartDto cartDto = this.modelMapper.map(cart, CartDto.class);

        return cartDto;
    }

    @Override
    public CartDto removeItemFromCart(String userName, int productId) {

        User user = this.userRepository.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException("User with given userName " + userName + "not found..!"));

        Cart cart = user.getCart();

        Set<CartItem> cartItems = cart.getCartItems();

        boolean result = cartItems.removeIf((cartItem -> cartItem.getProduct().getProductId() == productId));
        System.out.println(result);

        Cart updatedCart = this.cartRepository.save(cart);
        CartDto cartDto = this.modelMapper.map(updatedCart, CartDto.class);
        return cartDto;
    }
}
