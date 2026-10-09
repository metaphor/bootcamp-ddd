package com.newchiwan.council.bootcamp.ddd.modules.order.domain.model;

public interface Cashier {
    Payment checkout(Order order);
}
