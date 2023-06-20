package com.durys.jakub.personalrequestsservice.personalrequests.application;

import com.durys.jakub.personalrequestsservice.context.ContextProvider;
import com.durys.jakub.personalrequestsservice.events.DomainEvent;
import com.durys.jakub.personalrequestsservice.events.DomainEventPublisher;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.events.PersonalRequestCreatedEvent;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.PersonalRequestFieldConverter;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.PersonalRequestRepository;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out.PersonalRequestAttachmentEntity;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out.PersonalRequestEntity;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out.PersonalRequestFieldEntity;
import com.durys.jakub.personalrequestsservice.requestypes.domain.RequestTypeField;
import com.durys.jakub.personalrequestsservice.requestypes.infrastructure.RequestTypeRepository;
import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public void save(PersonalRequest personalRequest, List<MultipartFile> attachments) {

        PersonalRequestEntity entity = toEntity(personalRequest)
                .withFields(fieldsFrom(personalRequest))
                .withAttachments(buildAttachments(attachments));

        PersonalRequestEntity saved = personalRequestRepository.save(entity);

        eventPublisher.publish(new PersonalRequestCreatedEvent(saved.getId(), saved.getTenantId()));
    }

    private Set<PersonalRequestAttachmentEntity> buildAttachments(List<MultipartFile> attachments) {

       if (Objects.isNull(attachments)) {
           return Collections.emptySet();
       }

       return attachments.stream()
                .map(PersonalRequestAttachmentEntity::new)
                .collect(Collectors.toSet());
    }


    private PersonalRequestEntity toEntity(PersonalRequest personalRequest) {
        return PersonalRequestEntity.builder()
                .requestTypeId(personalRequest.getTypeId())
                .tenantId(personalRequest.getTenantId())
                .status(Status.A)
                .build();
    }

    private Set<PersonalRequestFieldEntity> fieldsFrom(PersonalRequest personalRequest) {

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
