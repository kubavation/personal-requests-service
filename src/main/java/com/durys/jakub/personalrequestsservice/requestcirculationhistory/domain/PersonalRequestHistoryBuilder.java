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
        return "";
    }

    private static String handleSentForAcceptationRequest(PersonalRequestStatusChangedEvent event) {
        return "";
    }

    private static String handleRejectedRequest(PersonalRequestStatusChangedEvent event) {
        return "";
    }

    private static String handleConfirmedRequest(PersonalRequestStatusChangedEvent event) {
        return "";
    }
}
