package com.med.customer.services;

import com.med.customer.dao.CustomerRepo;
import com.med.customer.documents.Customer;
import com.med.customer.dto.mapper.CustomerMapper;
import com.med.customer.exception.CustomerNotFoundException;
import com.med.customer.records.CustomerRequest;
import com.med.customer.records.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public String createCustomer(@Valid CustomerRequest request) {
        Customer customer = customerRepo.save(customerMapper.requestToCutomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        Customer customer = customerRepo.findById(request.id()).orElseThrow(
                () -> new CustomerNotFoundException(String.format("Customer with id %s not found", request.id()))
        );
        mergeCustomer(customer, request);
        customerRepo.save(customer);
    }

    public void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.adress() != null){
            customer.setAdress(request.adress());
        }
    }

    public List<CustomerResponse> getAllcustomers() {
        return customerRepo.findAll()
                .stream()
                .map(customerMapper::fromCustomerToResponse)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String id) {
        return customerRepo.findById(id)
                .isPresent();
    }

    public CustomerResponse getCustomer(String id) {
        return customerRepo.findById(id)
                .map(customerMapper::fromCustomerToResponse)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s not found", id)));
    }

    public void deleteCustomer(String customerId) {
        customerRepo.deleteById(customerId);
    }
}
