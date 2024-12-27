package com.med.order.service;

import com.med.order.communication.CustomerClient;
import com.med.order.dao.OrderRepo;
import com.med.order.dto.OrderRequest;
import com.med.order.dto.mappers.OrderMapper;
import com.med.order.entity.Order;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;

    public Integer createOrder(@Valid OrderRequest orderRequest) {
        // check the customer existence --> OpenFeign
        // purchase the products --> product-ms
        // persist order
        // persist order lines
        // start payment process
        // send the order confirmation --> notification-ms(kafka)
    }
}
