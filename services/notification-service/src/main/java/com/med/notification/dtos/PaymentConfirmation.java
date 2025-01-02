package com.med.notification.dtos;

import com.med.notification.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(

        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstname,

        String customerLastname,

        String customerEmail
) {
}
