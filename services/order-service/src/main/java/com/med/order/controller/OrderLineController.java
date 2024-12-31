package com.med.order.controller;

import com.med.order.dto.OrderLineResponse;
import com.med.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-line")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@RequestParam Integer orderId) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
    }


}
