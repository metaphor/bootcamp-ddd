package io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model;

import io.github.metaphor.council.bootcamp.ddd.framework.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderFactory {
    private final OrderRepository orderRepository;
    private final OrderProducts orderProducts;

    @Transactional(readOnly = true)
    public Order place(OrderDescription description, List<OrderLineDescription> lineDescriptions) {
        Map<String, ProductSnapshot> productsById = orderProducts.productsInLines(lineDescriptions);

        List<OrderLine> orderLines = lineDescriptions.stream()
            .map(line -> {
                if (!productsById.containsKey(line.getProduct().getId())) {
                    log.atWarn().log("Product not found, productId: {}", line.getProduct().getId());
                    throw new NotFoundException();
                }
                ProductSnapshot productSnapshot = productsById.get(line.getProduct().getId());

                return OrderLineDescription.builder()
                    .product(productSnapshot)
                    .quantity(line.getQuantity())
                    .build();
            })
            .map(OrderLine::from)
            .toList();

        String id = orderRepository.nextId();

        return Order.place(id, description, orderLines);
    }

}
