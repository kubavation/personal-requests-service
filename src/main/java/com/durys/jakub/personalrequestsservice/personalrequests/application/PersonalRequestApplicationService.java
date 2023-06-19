package com.durys.jakub.personalrequestsservice.personalrequests.application;

import com.durys.jakub.personalrequestsservice.context.ContextProvider;
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

    @Transactional
    public void save(PersonalRequest personalRequest, List<MultipartFile> attachments) {

        PersonalRequestEntity entity = toEntity(personalRequest);

        if (Objects.nonNull(attachments)) {
            entity.withAttachments(buildAttachments(attachments));
        }

        personalRequestRepository.save(entity);
    }

    private Set<PersonalRequestAttachmentEntity> buildAttachments(List<MultipartFile> attachments) {
       return attachments.stream()
                .map(attachment -> {
                    try {
                        return new PersonalRequestAttachmentEntity(attachment.getOriginalFilename(), attachment.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException("Error in creating attachment");
                    }
                }).collect(Collectors.toSet());
    }


    private PersonalRequestEntity toEntity(PersonalRequest personalRequest) {
        return PersonalRequestEntity.builder()
                .requestTypeId(personalRequest.getTypeId())
                .tenantId(personalRequest.getTenantId())
                .status(Status.A)
                //.fields(fieldsFrom(personalRequest))
                .build();
    }

    private Set<PersonalRequestFieldEntity> fieldsFrom(PersonalRequest personalRequest) {

        Set<RequestTypeField> fieldDefinitions = requestTypeRepository.fields(personalRequest.getTypeId());

        if (fieldDefinitions.size() != personalRequest.getFields().size()) {
            throw new RuntimeException("invalid field size");
        }
//
//        fieldDefinitions
//                .stream()
//                .map(definition -> personalRequest.fieldValue(definition.getName()))

        return null;

    }

}
