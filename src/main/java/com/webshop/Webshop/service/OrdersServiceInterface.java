package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersServiceInterface {

    Optional<Orders> getOrdersById(Long id);

    void deleteOrdersById(Long id);

    Orders createNewOrders(Orders orders);

    Orders updateNewOrders(Orders orders);

    List<Orders> getAllOrders();

}
