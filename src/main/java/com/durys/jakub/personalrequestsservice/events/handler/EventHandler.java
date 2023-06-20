package com.durys.jakub.personalrequestsservice.events.handler;

import com.durys.jakub.personalrequestsservice.events.DomainEvent;

public interface EventHandler<T extends DomainEvent> {
    void handle(T t);
}
