package com.ecom.service.impl;

import com.ecom.dao.CartRepository;
import com.ecom.dao.OrderRepository;
import com.ecom.dao.ProductRepository;
import com.ecom.dao.UserRepository;
import com.ecom.entity.*;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.OrderDto;
import com.ecom.payload.OrderRequest;
import com.ecom.service.OrderServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderServiceI {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderRequest orderRequest, String userName) {

        //Order order = this.modelMapper.map(orderDto, Order.class);

        User user = this.userRepository.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException("Resource with given userName " + userName + "not found..!"));

        int cartId = orderRequest.getCartId();
        String address = orderRequest.getAddress();

        Order order = new Order();
        
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart with given cartId " + cartId + "not found..!"));

        Set<CartItem> cartItems = cart.getCartItems();

        AtomicReference<Double> totalOderPrice = new AtomicReference<>(0.0);

        Set<OrderItem> orderItems = cartItems.stream().map((cartItem) -> {
             OrderItem orderItem = new OrderItem();
             orderItem.setProduct(cartItem.getProduct());
             orderItem.setQuantity(cartItem.getQuantity());
             orderItem.setTotalProductPrice(cartItem.getTotalProductPrice());
             orderItem.setOrder(order);
             totalOderPrice.set(totalOderPrice.get()+orderItem.getTotalProductPrice());

             //updating productQuntity after placing order
//            int productId = orderItem.getProduct().getProductId();
//
//            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with given productId " + productId + "not found..!"));
//            product.setProductPrice(product.);

            return orderItem;

        }).collect(Collectors.toSet());


        order.setOrderItems(orderItems);
        order.setBillingAddress(address);
        order.setPaymentStatus("NOT PAID..!");
        order.setOrderAmount(totalOderPrice.get());
        order.setOrderCreated(new Date());
        order.setOrderStatus("CREATED..!");
        order.setOrderDelivered(null);
        order.setUser(user);

        Order savedOrder = this.orderRepository.save(order);

        cart.getCartItems().clear();
        this.cartRepository.save(cart);

        OrderDto orderDto1 = this.modelMapper.map(savedOrder, OrderDto.class);

        return orderDto1;
    }

    @Override
    public List<OrderDto> getAllOrders() {

        List<Order> orderList = this.orderRepository.findAll();
        List<OrderDto> orderDtos = orderList.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());

        return orderDtos;
    }

    @Override
    public OrderDto getSingleOrder(int orderId) {

        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Resource with OrderId " + orderId + "not found..!"));

        OrderDto orderDto = this.modelMapper.map(order, OrderDto.class);

        return orderDto;
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, int orderId) {

        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Resource with OrderId " + orderId + "not found..!"));

        order.setOrderStatus(orderDto.getOrderStatus());
        order.setPaymentStatus(orderDto.getPaymentStatus());

        Order saveOrder = this.orderRepository.save(order);

        OrderDto orderDto1 = this.modelMapper.map(saveOrder, OrderDto.class);

        return orderDto1;
    }

    @Override
    public void deleteOrder(int orderId) {

        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Resource with OrderId " + orderId + "not found..!"));
        this.orderRepository.delete(order);

    }
}
