package com.newchiwan.council.bootcamp.ddd.modules.identity.application.mapper;

import com.newchiwan.council.bootcamp.ddd.modules.identity.application.representation.SecuredCustomer;
import com.newchiwan.council.bootcamp.ddd.modules.identity.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", source = "securedId")
    @Mapping(target = "name", source = "description.name")
    SecuredCustomer toSecuredCustomer(Customer customer);
}
