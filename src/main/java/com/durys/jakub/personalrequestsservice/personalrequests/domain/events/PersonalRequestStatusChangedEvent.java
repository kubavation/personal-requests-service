package com.durys.jakub.personalrequestsservice.personalrequests.domain.events;

import com.durys.jakub.personalrequestsservice.events.DomainEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestStatus;

public record PersonalRequestStatusChangedEvent(Long requestId, String supervisorId, PersonalRequestStatus status) implements PersonalRequestEvent {
}
