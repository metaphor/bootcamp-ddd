package io.github.metaphor.council.bootcamp.ddd.modules.order.infrastructure;

import io.github.metaphor.council.bootcamp.ddd.framework.commons.Uuids;
import io.github.metaphor.council.bootcamp.ddd.framework.persistence.QueryDslSupport;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Order;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryJPA extends QueryDslSupport implements OrderRepository {
    @Override
    public void save(Order order) {
        saveOrUpdate(order);
    }

    @Override
    public String nextId() {
        return Uuids.nextCompact();
    }

    @Override
    public Optional<Order> ofId(String id) {
        return loadById(Order.class, id);
    }
}
