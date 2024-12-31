package com.med.order.service;

import com.med.order.dao.OrderLineRepo;
import com.med.order.dto.OrderLineRequest;
import com.med.order.dto.OrderLineResponse;
import com.med.order.dto.mappers.OrderLineMapper;
import com.med.order.entity.Order;
import com.med.order.entity.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepo orderLineRepo;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine order = orderLineMapper.requestToOrderLine(orderLineRequest);
        return orderLineRepo.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepo.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::requestToOrderLineResponse)
                .collect(Collectors.toList());
    }
}
