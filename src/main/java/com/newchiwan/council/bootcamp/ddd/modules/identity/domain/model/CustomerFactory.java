package com.newchiwan.council.bootcamp.ddd.modules.identity.domain.model;

import liquibase.util.MD5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerFactory {
    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDescription description) {
        String nextId = customerRepository.nextId();
        String securedId = MD5Util.computeMD5(nextId);
        return Customer.builder()
            .id(nextId)
            .securedId(securedId)
            .description(description)
            .registeredAt(LocalDateTime.now())
            .build();
    }
}
