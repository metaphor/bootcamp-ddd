package io.github.metaphor.council.bootcamp.ddd.modules.inventory.domain.model;

import java.util.Optional;

public interface InventoryRepository {
    void save(Inventory inventory);

    Optional<Inventory> ofProduct(String productId);
}
