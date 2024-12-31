package com.med.product.dto.mapper;

import com.med.product.dto.ProductPurchaseResponse;
import com.med.product.dto.ProductRequest;
import com.med.product.dto.ProductResponse;
import com.med.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product reqToProduct(ProductRequest productRequest);

    ProductResponse productToResponse(Product product);

    ProductPurchaseResponse productToPuchasedProduct(Product product);
}
