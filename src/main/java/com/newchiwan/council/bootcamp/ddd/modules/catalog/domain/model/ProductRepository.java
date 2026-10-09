package com.newchiwan.council.bootcamp.ddd.modules.catalog.domain.model;

import java.util.List;

public interface ProductRepository {
    List<Product> ofIds(List<String> ids);

    void save(Product product);
}
