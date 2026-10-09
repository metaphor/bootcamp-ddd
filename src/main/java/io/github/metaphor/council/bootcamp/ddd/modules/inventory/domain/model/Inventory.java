package io.github.metaphor.council.bootcamp.ddd.modules.inventory.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "inventories")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@DynamicUpdate
public class Inventory {
    @Id
    private String id;
    private String productId;
    private int stock;

    public void stockOut(int quantity) {
        if (stock < quantity) {
            throw new OutOfStockException();
        }
        stock -= quantity;
    }
}
