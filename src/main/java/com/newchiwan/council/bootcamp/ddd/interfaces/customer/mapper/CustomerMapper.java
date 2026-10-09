package com.newchiwan.council.bootcamp.ddd.interfaces.customer.mapper;

import com.newchiwan.council.bootcamp.ddd.modules.identity.application.representation.SecuredCustomer;
import com.newchiwan.council.bootcamp.ddd.interfaces.customer.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse toCustomerResponse(SecuredCustomer securedCustomer);

}
