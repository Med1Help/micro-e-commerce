package com.med.order.controller;

import com.med.order.dto.OrderRequest;
import com.med.order.dto.OrderResponse;
import com.med.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping
    public ResponseEntity<OrderResponse> findById(@RequestParam Integer orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }
}
