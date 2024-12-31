package com.med.order.dto.mappers;

import com.med.order.dto.OrderLineRequest;
import com.med.order.dto.OrderLineResponse;
import com.med.order.entity.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderLineMapper {

    OrderLine requestToOrderLine(OrderLineRequest orderLineRequest);

    OrderLineResponse requestToOrderLineResponse(OrderLine orderLine);
}
