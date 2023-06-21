package com.durys.jakub.personalrequestsservice.requestcirculationhistory.application.handler;

import com.durys.jakub.personalrequestsservice.events.handler.EventHandler;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestStatusChangedEvent;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.PersonalRequestCirculationHistory;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.PersonalRequestHistoryBuilder;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.SupervisorAcceptationHistory;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure.PersonalRequestCirculationHistoryRepository;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure.SupervisorAcceptationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalRequestStatusChangedEventHandler implements EventHandler<PersonalRequestStatusChangedEvent> {

    private final PersonalRequestCirculationHistoryRepository personalRequestCirculationHistoryRepository;
    private final SupervisorAcceptationHistoryRepository supervisorAcceptationHistoryRepository;

    @Override
    @EventListener
    public void handle(PersonalRequestStatusChangedEvent event) {

        PersonalRequestCirculationHistory history = personalRequestCirculationHistoryRepository.findById(event.requestId())
                .map(requestHistory -> requestHistory.withHistory(PersonalRequestHistoryBuilder.buildFrom(event)))
                .map(personalRequestCirculationHistoryRepository::save)
                .orElseThrow(() -> new RuntimeException("todo"));


        supervisorAcceptationHistoryRepository.findByRequestId(event.requestId())
                .stream()
                .map(supervisorAcceptationHistory -> supervisorAcceptationHistory.withHistory(history.getHistory()))
                .forEach(supervisorAcceptationHistoryRepository::save);


        if (supervisorAcceptationHistoryRepository
                .findBySupervisorAndRequest(event.supervisorId(), event.requestId()).isEmpty()) {

            SupervisorAcceptationHistory supervisorHistory = SupervisorAcceptationHistory.builder()
                    .requestId(event.requestId())
                    .supervisorId(event.supervisorId())
                    .requestHistory(history.getHistory())
                    .build();

            supervisorAcceptationHistoryRepository.save(supervisorHistory);
        }



    }
}
