package com.smartinventory.inventoryservice.controller;

import com.smartinventory.inventoryservice.dto.OrderRequestDTO;
import com.smartinventory.inventoryservice.entity.Order;
import com.smartinventory.inventoryservice.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestBody OrderRequestDTO dto){
        return orderService.placeOrder(dto);
    }

    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable Long orderId){
        return orderService.cancelOrder(orderId);
    }
}