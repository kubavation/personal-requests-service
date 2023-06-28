package com.durys.jakub.personalrequestsservice.personalrequests.application;

import com.durys.jakub.personalrequestsservice.context.ContextProvider;
import com.durys.jakub.personalrequestsservice.events.DomainEventPublisher;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestField;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestCreatedEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestStatusChangedEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.PersonalRequestFieldConverter;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.PersonalRequestRepository;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequestDTO;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequestRejectionReason;
import com.durys.jakub.personalrequestsservice.requestypes.domain.RequestTypeField;
import com.durys.jakub.personalrequestsservice.requestypes.infrastructure.RequestTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.durys.jakub.personalrequestsservice.personalrequests.application.PersonalRequestConverter.buildAttachments;
import static com.durys.jakub.personalrequestsservice.personalrequests.application.PersonalRequestConverter.toEntity;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonalRequestApplicationService {

    private final ContextProvider contextProvider;
    private final RequestTypeRepository requestTypeRepository;
    private final PersonalRequestRepository personalRequestRepository;
    private final DomainEventPublisher eventPublisher;

    @Transactional
    public void save(PersonalRequestDTO personalRequestDTO, List<MultipartFile> attachments) {

        PersonalRequest entity = toEntity(personalRequestDTO)
                .withFields(fieldsFrom(personalRequestDTO))
                .withAttachments(buildAttachments(attachments));

        PersonalRequest saved = personalRequestRepository.save(entity);

        eventPublisher
                .publish(new PersonalRequestCreatedEvent(saved.getId(), saved.getTenantId()));
    }

    public void sendTo(Set<Long> requestsId, String supervisorId) {

        List<PersonalRequest> requests = personalRequestRepository.findAllById(requestsId)
                .stream()
                .map(request -> request.sendTo(supervisorId))
                .toList();

        personalRequestRepository.saveAll(requests)
                .forEach(this::emitPersonalRequestStatusChangedEvent);
    }

    public void confirm(Set<Long> requestsId) {

        List<PersonalRequest> requests = personalRequestRepository.findAllById(requestsId)
                .stream()
                .map(PersonalRequest::confirm)
                .toList();

        personalRequestRepository.saveAll(requests)
                .forEach(this::emitPersonalRequestStatusChangedEvent);
    }

    public void reject(Set<PersonalRequestRejectionReason> rejectionReasons) {

        Set<PersonalRequest> requests = rejectionReasons.stream()
                .map(reason ->
                        personalRequestRepository.findById(reason.getRequestId())
                                .map(request -> request.reject(reason.getReason()))
                                .orElseThrow(() -> new RuntimeException("entity not found")))
                .collect(Collectors.toSet());

        personalRequestRepository.saveAll(requests)
                .forEach(this::emitPersonalRequestStatusChangedEvent);
    }

    private void emitPersonalRequestStatusChangedEvent(PersonalRequest request) {
        eventPublisher.publish(
                new PersonalRequestStatusChangedEvent(request.getId(), request.getSupervisorId(), request.getStatus()));
    }


    private Set<PersonalRequestField> fieldsFrom(PersonalRequestDTO personalRequestDTO) {

        Set<RequestTypeField> fieldDefinitions = requestTypeRepository.fields(personalRequestDTO.getTypeId());

        if (fieldDefinitions.size() != personalRequestDTO.getFields().size()) {
            throw new RuntimeException("invalid field size");
        }

        return fieldDefinitions
                .stream()
                .map(definition -> PersonalRequestFieldConverter
                        .convert(definition, personalRequestDTO.fieldValue(definition.getName())))
                .collect(Collectors.toSet());
    }

}
