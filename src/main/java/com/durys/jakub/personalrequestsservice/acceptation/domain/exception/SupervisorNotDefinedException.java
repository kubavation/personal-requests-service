package com.durys.jakub.personalrequestsservice.acceptation.domain.exception;

import java.time.LocalDate;

public class SupervisorNotDefinedException extends RuntimeException {

    public SupervisorNotDefinedException(String tenantId, LocalDate at) {
        super("Supervisor not defined for user %s at %s".formatted(tenantId, at.toString()));
    }
}
