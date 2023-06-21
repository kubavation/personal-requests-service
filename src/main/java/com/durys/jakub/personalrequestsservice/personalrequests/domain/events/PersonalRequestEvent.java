package com.durys.jakub.personalrequestsservice.personalrequests.domain.events;

import com.durys.jakub.personalrequestsservice.events.DomainEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestStatus;

public interface PersonalRequestEvent extends DomainEvent {
    PersonalRequestStatus status();
}
