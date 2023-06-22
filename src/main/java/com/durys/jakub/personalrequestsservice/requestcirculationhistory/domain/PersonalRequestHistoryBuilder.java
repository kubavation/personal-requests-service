package com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestCreatedEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestStatusChangedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalRequestHistoryBuilder {

    public static String buildFrom(PersonalRequestEvent event) {
        return switch (event.status()) {
            case NEW -> handleNewRequest((PersonalRequestCreatedEvent) event);
            case SENT_FOR_ACCEPTATION -> handleSentForAcceptationRequest((PersonalRequestStatusChangedEvent) event);
            case REJECTED ->  handleRejectedRequest((PersonalRequestStatusChangedEvent) event);
            case CONFIRMED ->  handleConfirmedRequest((PersonalRequestStatusChangedEvent) event);
        };
    }

    private static String handleNewRequest(PersonalRequestCreatedEvent event) {
        return "%s - %s created new new request".formatted(event.status().name(), event.authorId());
    }

    private static String handleSentForAcceptationRequest(PersonalRequestStatusChangedEvent event) {
        return "%s - request sent for acceptation to %s".formatted(event.status().name(), event.supervisorId());
    }

    private static String handleRejectedRequest(PersonalRequestStatusChangedEvent event) {
        return "%s - request rejected by %s".formatted(event.status().name(), event.supervisorId());
    }

    private static String handleConfirmedRequest(PersonalRequestStatusChangedEvent event) {
        return "%s - request confirmed by %s".formatted(event.status().name(), event.supervisorId());
    }
}
