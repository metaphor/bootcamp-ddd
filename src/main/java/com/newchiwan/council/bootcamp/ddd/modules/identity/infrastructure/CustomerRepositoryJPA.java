package com.newchiwan.council.bootcamp.ddd.modules.identity.infrastructure;

import com.newchiwan.council.bootcamp.ddd.modules.identity.domain.model.Customer;
import com.newchiwan.council.bootcamp.ddd.modules.identity.domain.model.CustomerRepository;
import com.newchiwan.council.bootcamp.ddd.framework.commons.Uuids;
import com.newchiwan.council.bootcamp.ddd.framework.persistence.QueryDslSupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.newchiwan.council.bootcamp.ddd.modules.identity.domain.model.QCustomer.customer;
import static java.util.Optional.ofNullable;

@Repository
public class CustomerRepositoryJPA extends QueryDslSupport implements CustomerRepository {

    @Override
    public String nextId() {
        return Uuids.nextCompact();
    }

    @Override
    public void save(Customer customer) {
        saveOrUpdate(customer);
    }

    @Override
    public Optional<Customer> ofSecuredId(String securedId) {
        return ofNullable(
            selectFrom(customer).where(customer.securedId.eq(securedId)).fetchOne()
        );
    }
}
