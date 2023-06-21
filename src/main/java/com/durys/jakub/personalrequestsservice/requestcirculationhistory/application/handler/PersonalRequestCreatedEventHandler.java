package com.durys.jakub.personalrequestsservice.requestcirculationhistory.application.handler;

import com.durys.jakub.personalrequestsservice.events.handler.EventHandler;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestCreatedEvent;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.PersonalRequestCirculationHistory;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.PersonalRequestHistoryBuilder;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure.PersonalRequestCirculationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalRequestCreatedEventHandler implements EventHandler<PersonalRequestCreatedEvent> {

    private final PersonalRequestCirculationHistoryRepository repository;

    @Override
    @EventListener
    @Async
    public void handle(PersonalRequestCreatedEvent event) {

        PersonalRequestCirculationHistory requestHistory = PersonalRequestCirculationHistory.builder()
                .requestId(event.requestId())
                .tenantId(event.authorId())
                .history(PersonalRequestHistoryBuilder.buildFrom(event))
                .build();

        repository.save(requestHistory);
    }
}
