package com.newchiwan.council.bootcamp.ddd.modules.order.infrastructure;

import com.newchiwan.council.bootcamp.ddd.modules.inventory.application.InventoryApplicationService;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.Order;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.Stocker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class LocalStocker implements Stocker {
    private final InventoryApplicationService inventoryApplicationService;

    @Override
    @Transactional
    public void stockOut(Order order) {
        order.getLines().forEach(line -> {
            inventoryApplicationService.stockOut(line.getDescription().getProduct().getId(), line.getDescription().getQuantity());
        });
    }
}
