package io.github.metaphor.council.bootcamp.ddd.modules.order.application;

import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderDomainService;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Order;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderDescription;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderLineDescription;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PlaceOrderApplicationServiceOOVer {

    private final OrderDomainService orderDomainService;

    public Order place(OrderDescription description, List<OrderLineDescription> lineDescriptions) {
        return orderDomainService.place(description, lineDescriptions);
    }
}
