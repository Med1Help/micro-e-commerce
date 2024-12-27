package com.med.order.dao;

import com.med.order.entity.payment_method.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Long> {
    boolean existsByPaymentMethod(String paymentMethod);
}
