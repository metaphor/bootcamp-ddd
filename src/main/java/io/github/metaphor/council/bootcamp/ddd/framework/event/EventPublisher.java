package io.github.metaphor.council.bootcamp.ddd.framework.event;

public interface EventPublisher {
    <T> void publish(T event);
}
