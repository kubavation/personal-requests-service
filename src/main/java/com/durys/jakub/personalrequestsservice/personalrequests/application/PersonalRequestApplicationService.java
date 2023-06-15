package com.durys.jakub.personalrequestsservice.personalrequests.application;

import com.durys.jakub.personalrequestsservice.context.Context;
import com.durys.jakub.personalrequestsservice.context.ContextProvider;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.AbstractPersonalRequestFactory;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.Requestable;
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

    public Requestable patternFor(Type type) {

        Context context = contextProvider.context();

        log.info("context {}", context.name());

        return AbstractPersonalRequestFactory
                .instanceFrom(context, type, requestTypeRepository);
    }
}
