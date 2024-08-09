package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.OrderStatusDTO;
import com.webshop.Webshop.jpa.OrderStatus;
import com.webshop.Webshop.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/order/status")
@RequiredArgsConstructor
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusService.getAllOrderStatus();
    }

    @GetMapping("/{id}")
    public Optional<OrderStatus> getOrderStatusById(@PathVariable("id") Long id) {
        return orderStatusService.getOrderStatusById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewOrderStatus(OrderStatusDTO orderStatusDTO) {

        OrderStatus orderStatusRequest = modelMapper.map(orderStatusDTO, OrderStatus.class);

        OrderStatus newOrderStatus = orderStatusService.createNewOrders(orderStatusRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order Status created!! ID: " + newOrderStatus.getId());
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateOrderStatus(OrderStatusDTO orderStatusDTO) {

        OrderStatus orderStatusRequest = modelMapper.map(orderStatusDTO, OrderStatus.class);

        OrderStatus newOrderStatus = orderStatusService.updateNewOrders(orderStatusRequest);

        return ResponseEntity.status(HttpStatus.OK).body("Order Status updated!! ID: " + newOrderStatus.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderStatus(@PathVariable("id") Long id) {
        orderStatusService.deleteOrderStatusById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Order status deleted!!");

    }


}
