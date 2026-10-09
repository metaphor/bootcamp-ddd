package com.newchiwan.council.bootcamp.ddd.framework.event;

public interface EventPublisher {
    <T> void publish(T event);
}
