package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.OrderItemDTO;
import com.webshop.Webshop.jpa.OrderItem;
import com.webshop.Webshop.service.OrderItemService;
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
@RequestMapping("/v1/order/item")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerToken")
@Tag(name = "Order Item API", description = "The Order Item API ")
public class OrderItemController {

    private final OrderItemService orderItemService;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get ordered item by id", description = "Return ordered item by id from the database")
    @ApiResponse(responseCode = "200", description = "Get ordered item")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public Optional<OrderItem> getOrderItemById(@PathVariable("id") Long id){
        return orderItemService.getOrderItemById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all ordered item", description = "Return all ordered item from the database")
    @ApiResponse(responseCode = "200", description = "Get all ordered item")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public List<OrderItem> getAllOrderItem(){
        return orderItemService.getAllOrderItem();
    }

    @PostMapping("/new")
    @Operation(summary = "Create new order item", description = "Create new order item")
    @ApiResponse(responseCode = "201", description = "Order item created")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> createNewOrderItem(@RequestBody OrderItemDTO orderItemDTO){

        OrderItem orderItemRequest = modelMapper.map(orderItemDTO, OrderItem.class);

        OrderItem newOrderItem = orderItemService.createNewOrderItem(orderItemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("New ordered item added to the database! You can find it on this ID: " + newOrderItem.getId());

    }

    @PostMapping("/update")
    @Operation(summary = "Update order item", description = "Update order item")
    @ApiResponse(responseCode = "200", description = "Order item created")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> updateOrderItem(@RequestBody OrderItemDTO orderItemDTO){

        OrderItem orderItemRequest = modelMapper.map(orderItemDTO, OrderItem.class);

        OrderItem newOrderItem = orderItemService.updateNewOrderItem(orderItemRequest);

        return ResponseEntity.status(HttpStatus.OK).body("Ordered item updated! ID: " + newOrderItem.getId());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete order item", description = "Delete order item by id")
    @ApiResponse(responseCode = "200", description = "Order item deleted")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> deleteOrderItem(@PathVariable("id") Long id){
        orderItemService.deleteOrderItemById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Ordered Item deleted!!");
    }












}
