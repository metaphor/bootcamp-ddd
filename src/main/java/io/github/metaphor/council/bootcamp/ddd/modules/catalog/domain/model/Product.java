package io.github.metaphor.council.bootcamp.ddd.modules.catalog.domain.model;

import io.github.metaphor.council.bootcamp.ddd.framework.persistence.InjectableEntityListener;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Stocker;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@DynamicUpdate
@EntityListeners(InjectableEntityListener.class)
public class Product {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    @Embedded
    private ProductDescription description;

    @Transient
    private Stocker stocker;

    @Autowired
    public void setStocker(Stocker stocker) {
        this.stocker = stocker;
    }

    public void draft() {
        description.draft();
    }


    @Data
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @DynamicUpdate
    public static class ProductDescription {
        @Enumerated(EnumType.STRING)
        private ProductStatus status;
        private LocalDateTime publishedAt;

        public ProductDescription draft() {
            this.status = ProductStatus.DRAFT;
            return this;
        }
    }
}
