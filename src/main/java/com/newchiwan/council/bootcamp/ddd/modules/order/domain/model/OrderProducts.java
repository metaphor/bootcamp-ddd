package com.newchiwan.council.bootcamp.ddd.modules.order.domain.model;

import com.newchiwan.council.bootcamp.ddd.framework.domain.archtype.CapabilityProvider;

import java.util.List;
import java.util.Map;

@CapabilityProvider
public interface OrderProducts {
    Map<String, ProductSnapshot> productsInLines(List<OrderLineDescription> lineDescriptions);
}
