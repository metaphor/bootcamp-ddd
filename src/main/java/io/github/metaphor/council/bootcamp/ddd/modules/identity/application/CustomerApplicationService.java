package io.github.metaphor.council.bootcamp.ddd.modules.identity.application;

import io.github.metaphor.council.bootcamp.ddd.modules.identity.application.mapper.CustomerMapper;
import io.github.metaphor.council.bootcamp.ddd.modules.identity.application.representation.SecuredCustomer;
import io.github.metaphor.council.bootcamp.ddd.modules.identity.domain.model.Customer;
import io.github.metaphor.council.bootcamp.ddd.modules.identity.domain.model.CustomerDescription;
import io.github.metaphor.council.bootcamp.ddd.modules.identity.domain.model.CustomerFactory;
import io.github.metaphor.council.bootcamp.ddd.modules.identity.domain.model.CustomerRepository;
import io.github.metaphor.council.bootcamp.ddd.framework.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerApplicationService {

    private final CustomerRepository customerRepository;
    private final CustomerFactory customerFactory;

    @Transactional(readOnly = true)
    public SecuredCustomer ofSecuredId(String securedId) {
        return customerRepository.ofSecuredId(securedId)
            .map(CustomerMapper.INSTANCE::toSecuredCustomer)
            .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public SecuredCustomer register(CustomerDescription description) {

        Customer customer = customerFactory.createCustomer(description);

        customerRepository.save(customer);

        return CustomerMapper.INSTANCE.toSecuredCustomer(customer);
    }
}
