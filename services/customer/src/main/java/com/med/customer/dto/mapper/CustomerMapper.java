package com.med.customer.dto.mapper;

import com.med.customer.documents.Customer;
import com.med.customer.records.CustomerRequest;
import com.med.customer.records.CustomerResponse;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    Customer requestToCutomer(@Valid CustomerRequest request);

    CustomerResponse fromCustomerToResponse(Customer customer);
}
