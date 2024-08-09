package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.OrdersDTO;
import com.webshop.Webshop.jpa.Orders;
import com.webshop.Webshop.service.OrdersService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<Orders> getAllOrders(){
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Orders> getOrderById(@PathVariable("id") Long id){
        return ordersService.getOrdersById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewOrder(@RequestBody OrdersDTO ordersDTO){

        Orders orderRequest = modelMapper.map(ordersDTO, Orders.class);

        Orders newOrder = ordersService.createNewOrders(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("New order added to the database! ID: "+newOrder.getId());

    }

    @PostMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody OrdersDTO ordersDTO){

        Orders orderRequest = modelMapper.map(ordersDTO, Orders.class);

        Orders newOrder = ordersService.updateNewOrders(orderRequest);

        return ResponseEntity.status(HttpStatus.OK).body("Order updated!! ID: "+newOrder.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id){
        ordersService.deleteOrdersById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Order deleted!!");
    }


}
