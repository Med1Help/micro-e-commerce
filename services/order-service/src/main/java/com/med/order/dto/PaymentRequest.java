package com.med.order.dto;

import com.med.order.entity.payment_method.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,

        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        CustomerResponse customer
) {
}
