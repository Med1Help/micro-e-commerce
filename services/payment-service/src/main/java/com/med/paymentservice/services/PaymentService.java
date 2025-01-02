package com.med.paymentservice.services;

import com.med.paymentservice.daos.PaymentRepo;
import com.med.paymentservice.dtos.PaymentNotificationRequest;
import com.med.paymentservice.dtos.PaymentRequest;
import com.med.paymentservice.dtos.mappers.PaymentMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private NotificationProducer notificationProducer;
    private PaymentMapper paymentMapper;


    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepo.save(paymentMapper.requestToPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
