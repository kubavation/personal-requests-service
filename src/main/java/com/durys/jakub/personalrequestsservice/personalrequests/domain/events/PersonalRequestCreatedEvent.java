package com.durys.jakub.personalrequestsservice.personalrequests.domain.events;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestStatus;

public record  PersonalRequestCreatedEvent(Long requestId, String authorId) implements PersonalRequestEvent {

    @Override
    public PersonalRequestStatus status() {
        return PersonalRequestStatus.NEW;
    }
}
