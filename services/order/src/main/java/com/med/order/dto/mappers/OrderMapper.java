package com.med.order.dto.mappers;

import com.med.order.dto.OrderRequest;
import com.med.order.dto.OrderResponse;
import com.med.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Order requestToOrder(OrderRequest request);

    OrderResponse orderToOrderResponse(Order order);
}
