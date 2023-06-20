package com.durys.jakub.personalrequestsservice.requestcirculationhistory.application.handler;

import com.durys.jakub.personalrequestsservice.events.handler.EventHandler;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalRequestCreatedEventHandler implements EventHandler<PersonalRequestCreatedEvent> {

    @Override
    @EventListener
    public void handle(PersonalRequestCreatedEvent personalRequestCreatedEvent) {

    }
}
