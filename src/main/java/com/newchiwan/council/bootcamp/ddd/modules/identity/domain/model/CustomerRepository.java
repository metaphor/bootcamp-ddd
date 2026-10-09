package com.newchiwan.council.bootcamp.ddd.modules.identity.domain.model;

import java.util.Optional;

public interface CustomerRepository {
    String nextId();
    void save(Customer customer);
    Optional<Customer> ofSecuredId(String securedId);
}
