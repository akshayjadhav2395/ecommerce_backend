package com.ecom.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;
    private String orderStatus;
    private String paymentStatus;
    private Date orderCreated;
    private Date orderDelivered;
    private String billingAddress;
    private double orderAmount;

}
