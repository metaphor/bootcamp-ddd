package io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model;

import io.github.metaphor.council.bootcamp.ddd.framework.domain.archtype.CapabilityProvider;

@CapabilityProvider
public interface RiskChecker {
    void check(Order order);
}
