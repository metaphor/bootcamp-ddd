package io.github.metaphor.council.bootcamp.ddd.interfaces.customer.mapper;

import io.github.metaphor.council.bootcamp.ddd.modules.identity.application.representation.SecuredCustomer;
import io.github.metaphor.council.bootcamp.ddd.interfaces.customer.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse toCustomerResponse(SecuredCustomer securedCustomer);

}
