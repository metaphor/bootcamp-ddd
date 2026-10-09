package com.newchiwan.council.bootcamp.ddd.modules.order.domain.model;

import com.newchiwan.council.bootcamp.ddd.modules.catalog.domain.model.Product;
import jakarta.persistence.Column;
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
public class ProductSnapshot {
    @Column(name = "product_id")
    private String id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_price")
    private BigDecimal price;

    public static ProductSnapshot take(Product product) {
        return builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .build();
    }

    public static ProductSnapshot takeOnlyId(String id) {
        return builder()
            .id(id)
            .build();
    }
}
