package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

enum PersonalRequestNotification {
    REJECTION("Your request with id %d has been rejected"),
    CONFIRMATION("Your request with id %d has been confirmed"),
    SENT_FOR_ACCEPTATION("Request with id %d has been sent for acceptation");

    private final String content;

    PersonalRequestNotification(String content) {
        this.content = content;
    }

    public String content() {
        return content;
    }
}
