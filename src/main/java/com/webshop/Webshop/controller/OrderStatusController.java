package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.OrderStatusDTO;
import com.webshop.Webshop.jpa.OrderStatus;
import com.webshop.Webshop.service.OrderStatusService;
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
@RequestMapping("/v1/order/status")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerToken")
@Tag(name = "Order Status API", description = "The Order Status API ")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    @Operation(summary = "Get all order status", description = "Return all order status from the database")
    @ApiResponse(responseCode = "200", description = "Get all order status")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusService.getAllOrderStatus();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order status by id", description = "Return order status by id from the database")
    @ApiResponse(responseCode = "200", description = "Get order status by id")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public Optional<OrderStatus> getOrderStatusById(@PathVariable("id") Long id) {
        return orderStatusService.getOrderStatusById(id);
    }

    @PostMapping("/new")
    @Operation(summary = "Create new order status", description = "Create new order status")
    @ApiResponse(responseCode = "201", description = "New order status created")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> createNewOrderStatus(OrderStatusDTO orderStatusDTO) {

        OrderStatus orderStatusRequest = modelMapper.map(orderStatusDTO, OrderStatus.class);

        OrderStatus newOrderStatus = orderStatusService.createNewOrders(orderStatusRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order Status created!! ID: " + newOrderStatus.getId());
    }

    @PostMapping("/update")
    @Operation(summary = "Update order status", description = "Update order status")
    @ApiResponse(responseCode = "200", description = "Update order status")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> updateOrderStatus(OrderStatusDTO orderStatusDTO) {

        OrderStatus orderStatusRequest = modelMapper.map(orderStatusDTO, OrderStatus.class);

        OrderStatus newOrderStatus = orderStatusService.updateNewOrders(orderStatusRequest);

        return ResponseEntity.status(HttpStatus.OK).body("Order Status updated!! ID: " + newOrderStatus.getId());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete order status", description = "Delete order status by id")
    @ApiResponse(responseCode = "201", description = "Order status deleted")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> deleteOrderStatus(@PathVariable("id") Long id) {
        orderStatusService.deleteOrderStatusById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Order status deleted!!");

    }


}
