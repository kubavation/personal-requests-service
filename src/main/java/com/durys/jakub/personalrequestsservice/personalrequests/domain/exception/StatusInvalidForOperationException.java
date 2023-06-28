package com.durys.jakub.personalrequestsservice.personalrequests.domain.exception;

public class StatusInvalidForOperationException extends RuntimeException {

    public StatusInvalidForOperationException() {
        super("Invalid status for this operation");
    }
}
