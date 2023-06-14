package com.durys.jakub.personalrequestsservice.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ContextProvider {

    @Value("${application.context}")
    private String context;

    public Context context() {
        return new Context(context);
    }

}
