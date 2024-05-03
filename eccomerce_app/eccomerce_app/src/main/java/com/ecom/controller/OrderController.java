package com.ecom.controller;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.OrderDto;
import com.ecom.payload.OrderRequest;
import com.ecom.payload.ProductDto;
import com.ecom.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    String userName="aks123@gmail.com";

    @PostMapping("/")
    public ResponseEntity<OrderDto> saveProduct(@RequestBody OrderRequest orderRequest)
    {
        OrderDto order = this.orderService.createOrder(orderRequest, userName);
        return new ResponseEntity<OrderDto>(order, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrder()
    {
        List<OrderDto> allOrders = this.orderService.getAllOrders();
        return new ResponseEntity<List<OrderDto>>(allOrders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getSingleOrder(@PathVariable int orderId)
    {
        OrderDto orderDto = this.orderService.getSingleOrder(orderId);
        return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
    }


    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable int orderId)
    {
        OrderDto orderDto1 = this.orderService.updateOrder(orderDto, orderId);
        return new ResponseEntity<OrderDto>(orderDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable int orderId)
    {
        this.orderService.deleteOrder(orderId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Order deleted successfully..!", false), HttpStatus.OK);
    }

}
