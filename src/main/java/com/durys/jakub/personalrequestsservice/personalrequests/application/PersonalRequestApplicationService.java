package com.durys.jakub.personalrequestsservice.personalrequests.application;

import com.durys.jakub.personalrequestsservice.context.ContextProvider;
import com.durys.jakub.personalrequestsservice.events.DomainEventPublisher;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestCreatedEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.PersonalRequestFieldConverter;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.PersonalRequestRepository;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestAttachment;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestField;
import com.durys.jakub.personalrequestsservice.requestypes.domain.RequestTypeField;
import com.durys.jakub.personalrequestsservice.requestypes.infrastructure.RequestTypeRepository;
import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonalRequestApplicationService {

    private final ContextProvider contextProvider;
    private final RequestTypeRepository requestTypeRepository;
    private final PersonalRequestRepository personalRequestRepository;
    private final DomainEventPublisher eventPublisher;

    @Transactional
    public void save(com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequest personalRequest, List<MultipartFile> attachments) {

        PersonalRequest entity = toEntity(personalRequest)
                .withFields(fieldsFrom(personalRequest))
                .withAttachments(buildAttachments(attachments));

        PersonalRequest saved = personalRequestRepository.save(entity);

        eventPublisher.publish(new PersonalRequestCreatedEvent(saved.getId(), saved.getTenantId()));
    }

    private Set<PersonalRequestAttachment> buildAttachments(List<MultipartFile> attachments) {

       if (Objects.isNull(attachments)) {
           return Collections.emptySet();
       }

       return attachments.stream()
                .map(PersonalRequestAttachment::new)
                .collect(Collectors.toSet());
    }


    private PersonalRequest toEntity(com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequest personalRequest) {
        return PersonalRequest.builder()
                .requestTypeId(personalRequest.getTypeId())
                .tenantId(personalRequest.getTenantId())
                .status(Status.A)
                .build();
    }

    private Set<PersonalRequestField> fieldsFrom(com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequest personalRequest) {

        Set<RequestTypeField> fieldDefinitions = requestTypeRepository.fields(personalRequest.getTypeId());

        if (fieldDefinitions.size() != personalRequest.getFields().size()) {
            throw new RuntimeException("invalid field size");
        }

        return fieldDefinitions
                .stream()
                .map(definition -> PersonalRequestFieldConverter
                        .convert(definition, personalRequest.fieldValue(definition.getName())))
                .collect(Collectors.toSet());
    }

}
