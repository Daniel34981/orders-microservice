package com.ecommerce.orders.infraestructure.out.jpa.mapper;

import com.ecommerce.orders.domain.model.Order;
import com.ecommerce.orders.infraestructure.out.jpa.entity.OrderEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
    @Mappings({
            @Mapping(target = "version", ignore = true)
    })
    OrderEntity toEntity(Order domain);
}
