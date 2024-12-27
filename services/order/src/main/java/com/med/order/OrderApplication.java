package com.med.order;

import com.med.order.dao.PaymentMethodRepo;
import com.med.order.entity.payment_method.PaymentMethod;
import com.med.order.entity.payment_method.PaymentMethodEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
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

