package com.durys.jakub.personalrequestsservice.personalrequests.application;

import com.durys.jakub.personalrequestsservice.context.Context;
import com.durys.jakub.personalrequestsservice.context.ContextProvider;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.AbstractPersonalRequestFactory;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.requestypes.domain.Type;
import com.durys.jakub.personalrequestsservice.requestypes.infrastructure.RequestTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonalRequestApplicationService {

    private final ContextProvider contextProvider;
    private final RequestTypeRepository requestTypeRepository;

    public PersonalRequest patternFor(Type type) {

        Context context = contextProvider.context();

        log.info("context {}", context.name());

        return AbstractPersonalRequestFactory
                .instanceFrom(context, type, requestTypeRepository);
    }
}
