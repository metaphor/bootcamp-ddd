package io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime placedAt;
    private LocalDateTime paidAt;
    private BigDecimal totalAmount;
    @Embedded
    private OrderDescription description;

    @Builder.Default
    @ElementCollection
    @JoinTable(name = "order_lines", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderLine> lines = new ArrayList<>();


    public static Order place(String id, OrderDescription description, List<OrderLine> orderLines) {

        return builder()
            .id(id)
            .description(description)
            .lines(orderLines)
            .totalAmount(orderLines.stream().map(OrderLine::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
            .placedAt(LocalDateTime.now())
            .status(OrderStatus.PLACED)
            .build();
    }

    public void checkout(Payment payment) {
        if (payment.getStatus() == PaymentStatus.PAID) {
            status = OrderStatus.PAID;
            paidAt = payment.getPaidAt();
        }
    }
}
