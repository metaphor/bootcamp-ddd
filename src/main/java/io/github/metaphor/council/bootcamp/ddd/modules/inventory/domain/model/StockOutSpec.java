package io.github.metaphor.council.bootcamp.ddd.modules.inventory.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StockOutSpec {
    private String productId;
    private int quantity;

    public static StockOutSpec from(String productId, int quantity) {
        return builder()
            .productId(productId)
            .quantity(quantity)
            .build();
    }
}
