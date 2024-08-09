package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.OrderItemDTO;
import com.webshop.Webshop.jpa.OrderItem;
import com.webshop.Webshop.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/order/item")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Optional<OrderItem> getOrderItemById(@PathVariable("id") Long id){
        return orderItemService.getOrderItemById(id);
    }

    @GetMapping("/all")
    public List<OrderItem> getAllOrderItem(){
        return orderItemService.getAllOrderItem();
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewOrderItem(@RequestBody OrderItemDTO orderItemDTO){

        OrderItem orderItemRequest = modelMapper.map(orderItemDTO, OrderItem.class);

        OrderItem newOrderItem = orderItemService.createNewOrderItem(orderItemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("New ordered item added to the database! You can find it on this ID: " + newOrderItem.getId());

    }

    @PostMapping("/update")
    public ResponseEntity<String> updateOrderItem(@RequestBody OrderItemDTO orderItemDTO){

        OrderItem orderItemRequest = modelMapper.map(orderItemDTO, OrderItem.class);

        OrderItem newOrderItem = orderItemService.updateNewOrderItem(orderItemRequest);

        return ResponseEntity.status(HttpStatus.OK).body("Ordered item updated! ID: " + newOrderItem.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable("id") Long id){
        orderItemService.deleteOrderItemById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Ordered Item deleted!!");
    }












}
