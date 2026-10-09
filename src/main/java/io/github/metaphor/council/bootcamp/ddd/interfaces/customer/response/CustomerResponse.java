package io.github.metaphor.council.bootcamp.ddd.interfaces.customer.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponse {
    private String id;
    private String name;
    private LocalDateTime registeredAt;
}
