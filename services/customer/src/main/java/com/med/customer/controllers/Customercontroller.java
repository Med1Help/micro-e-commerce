package com.med.customer.controllers;

import com.med.customer.records.CustomerRequest;
import com.med.customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class Customercontroller {

    private final CustomerService customerService;

    public Customercontroller(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Object> updateCustomer(@RequestBody @Valid CustomerRequest request){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(customerService.getAllcustomers());
    }

    @GetMapping("/exists")
    public ResponseEntity<Object> customerExistsById(@RequestParam String customerId){
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @GetMapping("/customer")
    public ResponseEntity<Object> getCustomerById(@RequestParam String customerId){
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @DeleteMapping("/customer")
    public ResponseEntity<Object> deleteCustomerById(@RequestParam String customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
