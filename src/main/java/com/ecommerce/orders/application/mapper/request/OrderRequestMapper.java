package com.ecommerce.orders.application.mapper.request;

import com.ecommerce.orders.application.dto.OrderRequest;
import com.ecommerce.orders.domain.model.Order;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),                 // lo genera la BD
            @Mapping(target = "productList", source = "productRequestList"),
            @Mapping(target = "totalPrice", ignore = true),         // lo calcula el caso de uso
            @Mapping(target = "status", ignore = true)
    })
    Order toOrder(OrderRequest orderRequest);
}
