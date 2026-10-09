package io.github.metaphor.council.bootcamp.ddd.modules.order.application;

import io.github.metaphor.council.bootcamp.ddd.framework.domain.exception.NotFoundException;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Cashier;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Order;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderRepository;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Payment;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CheckoutApplicationService {
    private final Cashier cashier;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment checkout(String orderId) {
        Order order = orderRepository.ofId(orderId).orElseThrow(NotFoundException::new);
        Payment payment = cashier.checkout(order);
        paymentRepository.save(payment);

        order.checkout(payment);
        return payment;
    }
}
