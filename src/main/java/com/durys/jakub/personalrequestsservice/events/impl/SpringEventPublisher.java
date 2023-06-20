package com.durys.jakub.personalrequestsservice.events.impl;

import com.durys.jakub.personalrequestsservice.events.DomainEvent;
import com.durys.jakub.personalrequestsservice.events.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class SpringEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
