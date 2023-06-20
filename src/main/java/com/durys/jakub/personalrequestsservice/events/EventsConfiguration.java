package com.durys.jakub.personalrequestsservice.events;

import com.durys.jakub.personalrequestsservice.events.impl.SpringEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EventsConfiguration {

    @Bean
    DomainEventPublisher eventPublisher(ApplicationEventPublisher eventPublisher) {
        return new SpringEventPublisher(eventPublisher);
    }

}
