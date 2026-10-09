package io.github.metaphor.council.bootcamp.ddd.modules.identity.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class CustomerDescription {
    private String name;

    public static CustomerDescription from(String name) {
        return builder()
            .name(name)
            .build();
    }
}
