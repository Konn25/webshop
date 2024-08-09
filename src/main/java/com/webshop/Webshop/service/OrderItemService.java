package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.OrderItem;
import com.webshop.Webshop.jpa.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService implements OrderItemServiceInterface{

    private final OrderItemRepository orderItemRepository;

    @Override
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public void deleteOrderItemById(Long id) {
        Optional<OrderItem> foundItem = orderItemRepository.findById(id);

        if (foundItem.isPresent()){
            orderItemRepository.deleteById(id);
        }
    }

    @Override
    public OrderItem createNewOrderItem(OrderItem orderItem) {
       return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateNewOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItem() {
        return orderItemRepository.findAll();
    }
}
