package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.OrdersDTO;
import com.webshop.Webshop.jpa.Orders;
import com.webshop.Webshop.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearerToken")
@Tag(name = "Orders API", description = "The Orders API ")
public class OrdersController {

    private final OrdersService ordersService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    @Operation(summary = "Get all order", description = "Return all order from the database")
    @ApiResponse(responseCode = "200", description = "Get all order")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public List<Orders> getAllOrders(){
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id", description = "Return order by id from the database")
    @ApiResponse(responseCode = "200", description = "Get order by id")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public Optional<Orders> getOrderById(@PathVariable("id") Long id){
        return ordersService.getOrdersById(id);
    }

    @PostMapping("/new")
    @Operation(summary = "Create new order", description = "Create new order")
    @ApiResponse(responseCode = "201", description = "New order created")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> createNewOrder(@RequestBody OrdersDTO ordersDTO){

        Orders orderRequest = modelMapper.map(ordersDTO, Orders.class);

        Orders newOrder = ordersService.createNewOrders(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("New order added to the database! ID: "+newOrder.getId());

    }

    @PostMapping("/update")
    @Operation(summary = "Update order", description = "Update order")
    @ApiResponse(responseCode = "200", description = "Order updated")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> updateOrder(@RequestBody OrdersDTO ordersDTO){

        Orders orderRequest = modelMapper.map(ordersDTO, Orders.class);

        Orders newOrder = ordersService.updateNewOrders(orderRequest);

        return ResponseEntity.status(HttpStatus.OK).body("Order updated!! ID: "+newOrder.getId());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete order by id", description = "Delete order by id from the database")
    @ApiResponse(responseCode = "200", description = "Deleted order by id")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id){
        ordersService.deleteOrdersById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Order deleted!!");
    }


}
