package com.newchiwan.council.bootcamp.ddd.framework.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringApplicationEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public <T> void publish(T event) {
        publisher.publishEvent(event);
    }
}
