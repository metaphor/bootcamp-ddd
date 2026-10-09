package com.newchiwan.council.bootcamp.ddd.modules.order.domain.model;

import com.newchiwan.council.bootcamp.ddd.framework.domain.archtype.CapabilityProvider;

@CapabilityProvider
public interface RiskChecker {
    void check(Order order);
}
