package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.Orders;
import com.webshop.Webshop.jpa.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService implements OrdersServiceInterface{

    private final OrdersRepository ordersRepository;


    @Override
    public Optional<Orders> getOrdersById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public void deleteOrdersById(Long id) {
        Optional<Orders> foundOrders = ordersRepository.findById(id);

        if (foundOrders.isPresent()){
            ordersRepository.deleteById(id);
        }
    }

    @Override
    public Orders createNewOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders updateNewOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
