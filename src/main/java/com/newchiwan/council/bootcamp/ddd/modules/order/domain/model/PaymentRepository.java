package com.newchiwan.council.bootcamp.ddd.modules.order.domain.model;

public interface PaymentRepository {
    void save(Payment payment);

    String nextId();
}
