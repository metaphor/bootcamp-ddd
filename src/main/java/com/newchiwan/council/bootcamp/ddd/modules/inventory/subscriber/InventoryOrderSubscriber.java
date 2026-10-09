package com.newchiwan.council.bootcamp.ddd.modules.inventory.subscriber;

import com.newchiwan.council.bootcamp.ddd.modules.inventory.domain.model.StockOutSpec;
import com.newchiwan.council.bootcamp.ddd.modules.inventory.application.InventoryApplicationService;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InventoryOrderSubscriber {

    private final InventoryApplicationService inventoryApplicationService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void on(OrderPlacedEvent event) {
        stockOut(event.getLines());
    }

    private void stockOut(List<OrderPlacedEvent.OrderLine> lines) {
        inventoryApplicationService.stockOut(lines.stream()
            .map(orderLine -> {
                String productId = orderLine.getProduct().getId();
                int quantity = orderLine.getQuantity();
                return StockOutSpec.from(productId, quantity);
            }).toList());
    }
}
