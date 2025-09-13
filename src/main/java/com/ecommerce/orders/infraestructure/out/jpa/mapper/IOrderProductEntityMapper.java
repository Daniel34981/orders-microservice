package com.ecommerce.orders.infraestructure.out.jpa.mapper;

import com.ecommerce.orders.domain.model.OrderProduct;
import com.ecommerce.orders.infraestructure.out.jpa.entity.OrderProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderProductEntityMapper {
    OrderProductEntity toEntity(OrderProduct item);
}
