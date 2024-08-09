package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderStatusServiceInterface {

    Optional<OrderStatus> getOrderStatusById(Long id);

    void deleteOrderStatusById(Long id);

    OrderStatus createNewOrders(OrderStatus orderStatus);

    OrderStatus updateNewOrders(OrderStatus orderStatus);

    List<OrderStatus> getAllOrderStatus();

}
