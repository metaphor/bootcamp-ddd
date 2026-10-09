package io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class OrderPlacedEvent {

    private String id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime placedAt;
    private String remark;
    private List<OrderLine> lines = new ArrayList<>();

    @Data
    public static class OrderLine {
        private ProductSnapshot product;
        private int quantity;
        private BigDecimal totalAmount;
    }

    @Data
    public static class ProductSnapshot {
        private String id;
        private String name;
        private BigDecimal price;
    }
}
