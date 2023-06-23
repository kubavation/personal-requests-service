package com.durys.jakub.personalrequestsservice.requestcirculationhistory.application.handler;

import com.durys.jakub.personalrequestsservice.events.handler.EventHandler;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestStatusChangedEvent;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.PersonalRequestCirculationHistory;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.PersonalRequestHistoryBuilder;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.SupervisorAcceptationHistory;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure.PersonalRequestCirculationHistoryRepository;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure.SupervisorAcceptationHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonalRequestStatusChangedEventHandler implements EventHandler<PersonalRequestStatusChangedEvent> {

    private final PersonalRequestCirculationHistoryRepository personalRequestCirculationHistoryRepository;
    private final SupervisorAcceptationHistoryRepository supervisorAcceptationHistoryRepository;

    @Override
    @EventListener
    @Async
    public void handle(PersonalRequestStatusChangedEvent event) {

        PersonalRequestCirculationHistory history = personalRequestCirculationHistoryRepository.findById(event.requestId())
                .map(requestHistory -> requestHistory.withHistory(PersonalRequestHistoryBuilder.buildFrom(event)))
                .map(personalRequestCirculationHistoryRepository::save)
                .orElseThrow(() -> new RuntimeException("todo"));


        supervisorAcceptationHistoryRepository.findByRequestId(event.requestId())
                .stream()
                .map(supervisorAcceptationHistory -> supervisorAcceptationHistory.withHistory(history.getHistory()))
                .forEach(supervisorAcceptationHistoryRepository::save);


        if (supervisorHistoryNotExists(event)) {
            supervisorAcceptationHistoryRepository.save(instanceOf(event, history));
        }

    }

    private static SupervisorAcceptationHistory instanceOf(PersonalRequestStatusChangedEvent event,
                                                           PersonalRequestCirculationHistory requestHistory) {
        return SupervisorAcceptationHistory.builder()
                .requestId(event.requestId())
                .supervisorId(event.supervisorId())
                .requestHistory(requestHistory.getHistory())
                .build();
    }

    private boolean supervisorHistoryNotExists(PersonalRequestStatusChangedEvent event) {
        return supervisorAcceptationHistoryRepository
                .findBySupervisorAndRequest(event.supervisorId(), event.requestId()).isEmpty()
    }

}
