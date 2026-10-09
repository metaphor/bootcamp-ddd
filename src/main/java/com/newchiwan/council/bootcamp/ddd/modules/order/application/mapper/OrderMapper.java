package com.newchiwan.council.bootcamp.ddd.modules.order.application.mapper;

import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.Order;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.OrderLine;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.OrderPlacedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "description.remark", target = "remark")
    OrderPlacedEvent toOrderPlacedEvent(Order order);


    @Mapping(source = "description.product", target = "product")
    @Mapping(source = "description.quantity", target = "quantity")
    OrderPlacedEvent.OrderLine toOrderPlacedEventOrderLine(OrderLine line);
}
