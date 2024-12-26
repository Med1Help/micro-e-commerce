package com.med.product.service;

import com.med.product.dao.ProductRepo;
import com.med.product.dto.ProductPurchaseRequest;
import com.med.product.dto.ProductPurchaseResponse;
import com.med.product.dto.ProductRequest;
import com.med.product.dto.ProductResponse;
import com.med.product.dto.mapper.ProductMapper;
import com.med.product.entity.Product;
import com.med.product.excception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public Integer createProduct(@Valid ProductRequest productRequest) {

        var product = productMapper.reqToProduct(productRequest);

        return productRepo.save(product).getId();
    }

    public ProductResponse getProduct(Integer productId) {

        return productRepo.findById(productId)
                .map(productMapper::productToResponse)
                .orElseThrow(()->new EntityNotFoundException("Product not found"));
    }

    public List<ProductResponse> getProducts() {

        return productRepo.findAll()
                .stream()
                .map(productMapper::productToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductPurchaseResponse> purchase(List<ProductPurchaseRequest> productPurchaseRequests) {

        var productIds = productPurchaseRequests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        List<Product> sortedProducts = productRepo.findAllByIdInOrderById(productIds);

        if (productIds.size() != sortedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        var sortedRequest = productPurchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        sortedProducts.parallelStream().forEach(product -> {
            ProductPurchaseRequest productPurchaseRequest = sortedRequest.stream().filter(p -> p.productId().equals(product.getId())).findFirst().orElse(null);

            if (productPurchaseRequest == null){
                throw new ProductPurchaseException("product request doesn't exist for product with ID: " + product.getId());
            }

            if (product.getAvailableQuantity() < productPurchaseRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + product.getId());
            }

            double newAvailableQuantity = product.getAvailableQuantity() - productPurchaseRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepo.save(product);
            purchasedProducts.add(productMapper.productToPuchasedProduct(product));
        });

        return purchasedProducts;
    }
}
