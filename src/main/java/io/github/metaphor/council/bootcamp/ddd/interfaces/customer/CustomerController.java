package io.github.metaphor.council.bootcamp.ddd.interfaces.customer;

import io.github.metaphor.council.bootcamp.ddd.modules.identity.application.CustomerApplicationService;
import io.github.metaphor.council.bootcamp.ddd.modules.identity.application.representation.SecuredCustomer;
import io.github.metaphor.council.bootcamp.ddd.modules.identity.domain.model.CustomerDescription;
import io.github.metaphor.council.bootcamp.ddd.interfaces.customer.mapper.CustomerMapper;
import io.github.metaphor.council.bootcamp.ddd.interfaces.customer.request.RegisterCustomerRequest;
import io.github.metaphor.council.bootcamp.ddd.interfaces.customer.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cust/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;

    @PostMapping
    public CustomerResponse register(@RequestBody RegisterCustomerRequest request) {
        SecuredCustomer securedCustomer = customerApplicationService.register(CustomerDescription.from(request.getName()));
        return CustomerMapper.INSTANCE.toCustomerResponse(securedCustomer);
    }

    @GetMapping("/{securedId}")
    public CustomerResponse customerOfSecuredId(@PathVariable String securedId) {
        SecuredCustomer securedCustomer = customerApplicationService.ofSecuredId(securedId);
        return CustomerMapper.INSTANCE.toCustomerResponse(securedCustomer);
    }
}
