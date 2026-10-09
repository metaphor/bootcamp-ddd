package io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);

    String nextId();

    Optional<Order> ofId(String id);
}
