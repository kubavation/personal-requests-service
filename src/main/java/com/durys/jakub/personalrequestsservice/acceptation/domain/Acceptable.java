package com.durys.jakub.personalrequestsservice.acceptation.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Acceptable {
    private boolean value;

    public static Acceptable from(boolean value) {
        return new Acceptable(value);
    }
}
