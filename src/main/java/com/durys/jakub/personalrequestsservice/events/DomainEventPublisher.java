package com.durys.jakub.personalrequestsservice.events;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
