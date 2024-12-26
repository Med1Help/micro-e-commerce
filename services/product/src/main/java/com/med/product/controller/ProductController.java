package com.med.product.controller;

import com.med.product.dto.ProductPurchaseRequest;
import com.med.product.dto.ProductPurchaseResponse;
import com.med.product.dto.ProductRequest;
import com.med.product.dto.ProductResponse;
import com.med.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody  List<ProductPurchaseRequest> productPurchaseRequests) {
        return ResponseEntity.ok(productService.purchase(productPurchaseRequests));
    }

    @GetMapping
    public ResponseEntity<ProductResponse> getProduct(@RequestParam Integer productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }
}
