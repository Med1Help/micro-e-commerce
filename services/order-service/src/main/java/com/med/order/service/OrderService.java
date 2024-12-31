package com.med.order.service;

import com.med.order.communication.CustomerClient;
import com.med.order.communication.PaymentClient;
import com.med.order.dao.OrderRepo;
import com.med.order.dto.*;
import com.med.order.dto.mappers.OrderMapper;
import com.med.order.entity.Order;
import com.med.order.exception.BusinessException;
import com.med.order.kafka.OrderProducer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductService productService;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProducts = productService.purchaseProducts(request.products());

        var order = orderRepo.save(orderMapper.requestToOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(orderMapper::orderToOrderResponse)
                .collect(Collectors.toList());
    }


    public OrderResponse findById(Integer id) {
        return orderRepo.findById(id)
                .map(orderMapper::orderToOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
