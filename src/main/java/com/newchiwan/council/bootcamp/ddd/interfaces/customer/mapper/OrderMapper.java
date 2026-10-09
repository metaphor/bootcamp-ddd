package com.newchiwan.council.bootcamp.ddd.interfaces.customer.mapper;

import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.Order;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.OrderDescription;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.OrderLine;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.OrderLineDescription;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.ProductSnapshot;
import com.newchiwan.council.bootcamp.ddd.interfaces.customer.request.PlaceOrderRequest;
import com.newchiwan.council.bootcamp.ddd.interfaces.customer.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "description.remark", target = "remark")
    OrderResponse toOrderResponse(Order order);


    @Mapping(source = "description.product", target = "product")
    @Mapping(source = "description.quantity", target = "quantity")
    OrderResponse.OrderLine toOrderResponseOrderLine(OrderLine line);

    OrderDescription toOrderDescription(PlaceOrderRequest request);

    default List<OrderLineDescription> toOrderLineDescriptions(List<PlaceOrderRequest.OrderLine> lines) {
        return lines.stream().map(orderLine -> {
            ProductSnapshot productSnapshot = ProductSnapshot.takeOnlyId(orderLine.getProductId());
            return OrderLineDescription.builder()
                .product(productSnapshot)
                .quantity(orderLine.getQuantity())
                .build();
        }).toList();
    }
}
