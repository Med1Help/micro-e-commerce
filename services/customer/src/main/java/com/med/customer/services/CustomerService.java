package com.med.customer.services;

import com.med.customer.dao.CustomerRepo;
import com.med.customer.records.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepo customerRepo;

    public String createCustomer(@Valid CustomerRequest request) {
        return null;
    }
}
