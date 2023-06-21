package com.durys.jakub.personalrequestsservice.requestcirculationhistory.application.handler;

import com.durys.jakub.personalrequestsservice.events.handler.EventHandler;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestCreatedEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestSentForAcceptationEvent;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.PersonalRequestCirculationHistory;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.SupervisorAcceptationHistory;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure.PersonalRequestCirculationHistoryRepository;
import com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure.SupervisorAcceptationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonalRequestSentForAcceptationEventHandler implements EventHandler<PersonalRequestSentForAcceptationEvent> {

    private final PersonalRequestCirculationHistoryRepository personalRequestCirculationHistoryRepository;
    private final SupervisorAcceptationHistoryRepository supervisorAcceptationHistoryRepository;

    @Override
    @EventListener
    public void handle(PersonalRequestSentForAcceptationEvent event) {

        PersonalRequestCirculationHistory history = personalRequestCirculationHistoryRepository.findById(event.requestId())
                .orElseThrow(() -> new RuntimeException("todo"));

        history.setHistory("todo append");

        PersonalRequestCirculationHistory saved = personalRequestCirculationHistoryRepository.save(history);

        supervisorAcceptationHistoryRepository.findByRequestId(event.requestId())
                .stream()
                .map(supervisorAcceptationHistory -> supervisorAcceptationHistory.withHistory(saved.getHistory()))
                .forEach(supervisorAcceptationHistoryRepository::save);


        if (supervisorAcceptationHistoryRepository
                .findBySupervisorAndRequest(event.supervisorId(), event.requestId()).isEmpty()) {

            SupervisorAcceptationHistory supervisorHistory = SupervisorAcceptationHistory.builder()
                    .requestId(event.requestId())
                    .supervisorId(event.supervisorId())
                    .requestHistory(saved.getHistory())
                    .build();

            supervisorAcceptationHistoryRepository.save(supervisorHistory);
        }



    }
}
