package io.github.metaphor.council.bootcamp.ddd.modules.order.infrastructure;

import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Cashier;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Order;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Payment;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.PaymentRepository;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Log4j2
public class MockCashier implements Cashier {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment checkout(Order order) {
        return Payment.builder()
            .id(paymentRepository.nextId())
            .orderId(order.getId())
            .status(PaymentStatus.PAID)
            .paidAt(LocalDateTime.now())
            .amount(order.getTotalAmount())
            .build();
    }
}
