package com.med.product.service;

import com.med.product.dto.ProductRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public Integer createProduct(@Valid ProductRequest productRequest) {
    }
}
