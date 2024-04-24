package com.ecom.service;

import com.ecom.payload.OrderDto;

import java.util.List;

public interface OrderServiceI {

    public OrderDto createOrder(OrderDto orderDto);
    public List<OrderDto> getAllOrders();
    public OrderDto getSingleOrder(int orderId);
    public OrderDto updateOrder(OrderDto orderDto, int orderId);
    public void deleteOrder(int orderId);

}
