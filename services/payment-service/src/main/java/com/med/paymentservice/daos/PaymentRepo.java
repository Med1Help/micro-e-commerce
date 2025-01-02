package com.med.paymentservice.daos;

import com.med.paymentservice.enteties.payment_method.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

}
