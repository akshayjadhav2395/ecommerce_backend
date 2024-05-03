package com.ecom.service;

import com.ecom.payload.OrderDto;
import com.ecom.payload.OrderRequest;

import java.util.List;

public interface OrderServiceI {

    public OrderDto createOrder(OrderRequest orderRequest, String userName);
    public List<OrderDto> getAllOrders();
    public OrderDto getSingleOrder(int orderId);
    public OrderDto updateOrder(OrderDto orderDto, int orderId);
    public void deleteOrder(int orderId);

}
