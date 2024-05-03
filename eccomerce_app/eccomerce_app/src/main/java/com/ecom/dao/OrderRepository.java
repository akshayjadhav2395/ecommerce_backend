package com.ecom.dao;

import com.ecom.entity.Order;
import com.ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public Order findByOrderId(int orderId);
    public List<Order> findByBillingAddress(String billingAddress);
    public List<Order> findByUser(User user);
}
