package com.durys.jakub.personalrequestsservice.personalrequests.application;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestAttachment;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequestDTO;
import com.durys.jakub.personalrequestsservice.shared.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PersonalRequestConverter {


    public static PersonalRequest toEntity(PersonalRequestDTO personalRequestDTO) {
        return PersonalRequest.builder()
                .requestTypeId(personalRequestDTO.getTypeId())
                .tenantId(personalRequestDTO.getTenantId())
                .status(Status.A)
                .build();
    }

    public static Set<PersonalRequestAttachment> buildAttachments(List<MultipartFile> attachments) {

        if (Objects.isNull(attachments)) {
            return Collections.emptySet();
        }

        return attachments.stream()
                .map(PersonalRequestAttachment::new)
                .collect(Collectors.toSet());
    }
}
