package com.med.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.med.order.entity.payment_method.PaymentMethod;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {

}
