package com.newchiwan.council.bootcamp.ddd.modules.order.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Embeddable
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderLine {
    private OrderLineDescription description;
    private BigDecimal totalAmount;

    public static OrderLine from(OrderLineDescription description) {
        return builder()
            .description(description)
            .totalAmount(description.getProduct().getPrice().multiply(BigDecimal.valueOf(description.getQuantity())))
            .build();
    }
}
