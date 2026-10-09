package io.github.metaphor.council.bootcamp.ddd.modules.order.infrastructure;

import io.github.metaphor.council.bootcamp.ddd.framework.commons.Uuids;
import io.github.metaphor.council.bootcamp.ddd.framework.persistence.QueryDslSupport;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Payment;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.PaymentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryJPA extends QueryDslSupport implements PaymentRepository {
    @Override
    public void save(Payment payment) {
        saveOrUpdate(payment);
    }

    @Override
    public String nextId() {
        return Uuids.nextCompact();
    }
}
