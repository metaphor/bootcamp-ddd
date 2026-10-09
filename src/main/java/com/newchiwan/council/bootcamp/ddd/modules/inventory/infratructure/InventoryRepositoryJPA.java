package com.newchiwan.council.bootcamp.ddd.modules.inventory.infratructure;

import com.newchiwan.council.bootcamp.ddd.framework.persistence.QueryDslSupport;
import com.newchiwan.council.bootcamp.ddd.modules.inventory.domain.model.Inventory;
import com.newchiwan.council.bootcamp.ddd.modules.inventory.domain.model.InventoryRepository;
import jakarta.persistence.LockModeType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.newchiwan.council.bootcamp.ddd.modules.inventory.domain.model.QInventory.inventory;

@Repository
public class InventoryRepositoryJPA extends QueryDslSupport implements InventoryRepository {
    @Override
    public void save(Inventory inventory) {
        saveOrUpdate(inventory);
    }

    @Override
    public Optional<Inventory> ofProduct(String productId) {
        return Optional.ofNullable(
            selectFrom(inventory)
                .where(inventory.productId.eq(productId))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne()
        );
    }
}
