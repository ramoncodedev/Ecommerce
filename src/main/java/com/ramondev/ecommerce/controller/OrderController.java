package com.ramondev.ecommerce.controller;

import com.ramondev.ecommerce.dto.request.OrderRequest;
import com.ramondev.ecommerce.dto.response.OrderResponse;
import com.ramondev.ecommerce.mapper.OrderMapper;
import com.ramondev.ecommerce.model.entity.Order;
import com.ramondev.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(OrderMapper.toResponse(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<Order> orders = orderService.findAll();
        List<OrderResponse> response = orders.stream()
                .map(OrderMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(OrderMapper.toResponse(order));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService.findByUserId(userId);
        List<OrderResponse> response = orders.stream()
                .map(OrderMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        Order updatedOrder = orderService.updateOrder(id, request);
        return ResponseEntity.ok(OrderMapper.toResponse(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
