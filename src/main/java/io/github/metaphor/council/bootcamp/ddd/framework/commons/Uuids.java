package io.github.metaphor.council.bootcamp.ddd.framework.commons;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class Uuids {
    public static String nextCompact() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
