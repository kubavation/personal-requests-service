package com.durys.jakub.personalrequestsservice.personalrequests.domain.events;

import com.durys.jakub.personalrequestsservice.events.DomainEvent;

public record PersonalRequestCreatedEvent(Long requestId, String authorId) implements DomainEvent {
}
