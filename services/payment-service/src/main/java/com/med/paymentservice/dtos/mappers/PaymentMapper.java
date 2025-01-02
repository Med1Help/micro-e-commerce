package com.med.paymentservice.dtos.mappers;

import com.med.paymentservice.dtos.PaymentRequest;
import com.med.paymentservice.enteties.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    Payment requestToPayment(PaymentRequest paymentRequest);
}
