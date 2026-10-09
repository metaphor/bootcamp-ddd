package io.github.metaphor.council.bootcamp.ddd.interfaces.customer;

import io.github.metaphor.council.bootcamp.ddd.interfaces.customer.request.PlaceOrderRequest;
import io.github.metaphor.council.bootcamp.ddd.interfaces.customer.response.OrderResponse;
import io.github.metaphor.council.bootcamp.ddd.modules.order.application.CheckoutApplicationService;
import io.github.metaphor.council.bootcamp.ddd.modules.order.application.PlaceOrderApplicationService;
import io.github.metaphor.council.bootcamp.ddd.modules.order.application.PlaceOrderApplicationServiceOOVer;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Order;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderDescription;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderLineDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.github.metaphor.council.bootcamp.ddd.interfaces.customer.mapper.OrderMapper.INSTANCE;

@RestController
@RequestMapping("/cust/orders")
@RequiredArgsConstructor
public class OrderController {

    private final PlaceOrderApplicationServiceOOVer placeOrderApplicationServiceOOVer;
    private final PlaceOrderApplicationService placeOrderApplicationService;
    private final CheckoutApplicationService checkoutApplicationService;

    @PostMapping
    public OrderResponse place(@RequestBody PlaceOrderRequest request) {
        List<OrderLineDescription> lineDescriptions = INSTANCE.toOrderLineDescriptions(request.getLines());
        OrderDescription description = INSTANCE.toOrderDescription(request);
        Order order = placeOrderApplicationService.place(description, lineDescriptions);
        return INSTANCE.toOrderResponse(order);
    }

    @PostMapping("/{id}/checkout")
    public void checkout(@PathVariable(value = "id") String id) {
        checkoutApplicationService.checkout(id);
    }

    @PostMapping("/oo-version")
    public OrderResponse placeOOVersion(@RequestBody PlaceOrderRequest request) {
        List<OrderLineDescription> lineDescriptions = INSTANCE.toOrderLineDescriptions(request.getLines());
        OrderDescription description = INSTANCE.toOrderDescription(request);
        Order order = placeOrderApplicationServiceOOVer.place(description, lineDescriptions);
        return INSTANCE.toOrderResponse(order);
    }
}
