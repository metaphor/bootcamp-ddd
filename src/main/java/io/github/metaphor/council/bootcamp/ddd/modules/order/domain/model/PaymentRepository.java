package io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model;

public interface PaymentRepository {
    void save(Payment payment);

    String nextId();
}
