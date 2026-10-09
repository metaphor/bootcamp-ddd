package io.github.metaphor.council.bootcamp.ddd.modules.catalog.infrastructure;

import io.github.metaphor.council.bootcamp.ddd.framework.persistence.QueryDslSupport;
import io.github.metaphor.council.bootcamp.ddd.modules.catalog.domain.model.Product;
import io.github.metaphor.council.bootcamp.ddd.modules.catalog.domain.model.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.github.metaphor.council.bootcamp.ddd.modules.catalog.domain.model.QProduct.product;

@Repository
public class ProductRepositoryJPA extends QueryDslSupport implements ProductRepository {
    @Override
    public List<Product> ofIds(List<String> ids) {
        return selectFrom(product)
            .where(product.id.in(ids))
            .limit(10)
            .fetch();
    }

    @Override
    public void save(Product product) {
        saveOrUpdate(product);
    }
}
