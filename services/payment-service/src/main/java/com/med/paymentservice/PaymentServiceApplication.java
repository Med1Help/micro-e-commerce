package com.med.paymentservice;

import com.med.paymentservice.daos.PaymentMethodRepo;
import com.med.paymentservice.enteties.payment_method.PaymentMethod;
import com.med.paymentservice.enteties.payment_method.PaymentMethodEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner checkAndAddPaymentMethods(PaymentMethodRepo paymentMethodRepository) {
		return args -> {
			for (PaymentMethodEnum paymentMethodEnum : PaymentMethodEnum.values()) {
				String paymentMethodName = paymentMethodEnum.name();
				if (!paymentMethodRepository.existsByPaymentMethod(paymentMethodName)) {
					PaymentMethod paymentMethod = PaymentMethod
							.builder()
							.paymentMethod(paymentMethodName)
							.build();
					paymentMethodRepository.save(paymentMethod);
					System.out.println("Added missing payment method: " + paymentMethodName);
				}
			}
		};
	}
}
