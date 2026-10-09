package io.github.metaphor.council.bootcamp.ddd.modules.inventory.application;

import io.github.metaphor.council.bootcamp.ddd.modules.inventory.domain.model.Inventory;
import io.github.metaphor.council.bootcamp.ddd.modules.inventory.domain.model.InventoryRepository;
import io.github.metaphor.council.bootcamp.ddd.modules.inventory.domain.model.InventoryStockOutEvent;
import io.github.metaphor.council.bootcamp.ddd.modules.inventory.domain.model.StockOutSpec;
import io.github.metaphor.council.bootcamp.ddd.framework.domain.exception.NotFoundException;
import io.github.metaphor.council.bootcamp.ddd.framework.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryApplicationService {

    private final InventoryRepository inventoryRepository;
    private final EventPublisher eventPublisher;

    @Transactional
    public void stockOut(String productId, int quantity) {
        doStockOut(productId, quantity);
    }

    @Transactional
    public void stockOut(List<StockOutSpec> specs) {
        specs.forEach(spec -> doStockOut(spec.getProductId(), spec.getQuantity()));
    }

    private void doStockOut(String productId, int quantity) {
        Inventory inventory = inventoryRepository.ofProduct(productId).orElseThrow(NotFoundException::new);
        inventory.stockOut(quantity);
        inventoryRepository.save(inventory);
        eventPublisher.publish(toInventoryStockOutEvent(inventory));
    }

    private InventoryStockOutEvent toInventoryStockOutEvent(Inventory inventory) {
        return new InventoryStockOutEvent();
    }
}
