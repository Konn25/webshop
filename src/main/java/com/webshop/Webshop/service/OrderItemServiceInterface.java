package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemServiceInterface {

    Optional<OrderItem> getOrderItemById(Long id);

    void deleteOrderItemById(Long id);

    OrderItem createNewOrderItem(OrderItem orderItem);

    OrderItem updateNewOrderItem(OrderItem orderItem);

    List<OrderItem> getAllOrderItem();

}
