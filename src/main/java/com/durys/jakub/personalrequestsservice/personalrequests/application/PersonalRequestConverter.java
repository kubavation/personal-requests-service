package com.durys.jakub.personalrequestsservice.personalrequests.application;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequestDTO;
import com.durys.jakub.personalrequestsservice.shared.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PersonalRequestConverter {


    public static PersonalRequest toEntity(PersonalRequestDTO personalRequestDTO) {
        return PersonalRequest.builder()
                .requestTypeId(personalRequestDTO.getTypeId())
                .tenantId(personalRequestDTO.getTenantId())
                .status(Status.A)
                .build();
    }
}
