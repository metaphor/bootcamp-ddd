package io.github.metaphor.council.bootcamp.ddd.modules.identity.application.representation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SecuredCustomer {
    private String id;
    private String name;
    private LocalDateTime registeredAt;
}
