package com.ecom.service.impl;

import com.ecom.dao.OrderRepository;
import com.ecom.entity.Order;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.OrderDto;
import com.ecom.service.OrderServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderServiceI {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        Order order = this.modelMapper.map(orderDto, Order.class);
        Order saveOrder = this.orderRepository.save(order);

        OrderDto orderDto1 = this.modelMapper.map(saveOrder, OrderDto.class);

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

        order.setOrderDelivered(orderDto.getOrderDelivered());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setBillingAddress(orderDto.getBillingAddress());
        order.setPaymentStatus(orderDto.getPaymentStatus());
        order.setOrderCreated(orderDto.getOrderCreated());
        order.setOrderAmount(orderDto.getOrderAmount());

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
