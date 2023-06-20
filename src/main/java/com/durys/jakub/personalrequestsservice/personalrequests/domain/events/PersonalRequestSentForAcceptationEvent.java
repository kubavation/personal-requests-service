package com.durys.jakub.personalrequestsservice.personalrequests.domain.events;

import com.durys.jakub.personalrequestsservice.events.DomainEvent;

public record PersonalRequestSentForAcceptationEvent(Long requestId, String supervisorId) implements DomainEvent {
}
