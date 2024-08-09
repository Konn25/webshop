package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.OrderStatus;
import com.webshop.Webshop.jpa.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderStatusService implements OrderStatusServiceInterface {

    private final OrderStatusRepository orderStatusRepository;

    @Override
    public Optional<OrderStatus> getOrderStatusById(Long id) {
        return orderStatusRepository.findById(id);
    }

    @Override
    public void deleteOrderStatusById(Long id) {
        Optional<OrderStatus> foundStatus = orderStatusRepository.findById(id);

        if (foundStatus.isPresent()) {
            orderStatusRepository.deleteById(id);
        }
    }

    @Override
    public OrderStatus createNewOrders(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);

    }

    @Override
    public OrderStatus updateNewOrders(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }
}
