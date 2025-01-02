package com.med.paymentservice.daos;

import com.med.paymentservice.enteties.payment_method.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Long> {
    boolean existsByPaymentMethod(String paymentMethodName);
}
