package io.github.metaphor.council.bootcamp.ddd.modules.order.infrastructure;

import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderLineDescription;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderProducts;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.ProductSnapshot;
import io.github.metaphor.council.bootcamp.ddd.modules.catalog.domain.model.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LocalOrderProducts implements OrderProducts {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Map<String, ProductSnapshot> productsInLines(List<OrderLineDescription> lineDescriptions) {
        List<String> productIds = lineDescriptions.stream().map(desc -> desc.getProduct().getId()).toList();
        return productRepository.ofIds(productIds).stream()
            .map(ProductSnapshot::take)
            .collect(Collectors.toMap(ProductSnapshot::getId, p -> p));
    }
}
