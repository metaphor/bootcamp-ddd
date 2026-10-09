package io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class OrderDomainService {
    private final OrderRepository orderRepository;
    private final RiskChecker riskChecker;
    private final OrderFactory orderFactory;
    private final Stocker stocker;
    private final TransactionTemplate transactionTemplate;

    public Order place(OrderDescription description, List<OrderLineDescription> lineDescriptions) {

        Order order = orderFactory.place(description, lineDescriptions);

        riskChecker.check(order);

        transactionTemplate.executeWithoutResult(status -> {
            stocker.stockOut(order);
            orderRepository.save(order);
        });
        return order;
    }


}
