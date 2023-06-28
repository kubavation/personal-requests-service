package com.durys.jakub.personalrequestsservice.acceptation.domain;

import com.durys.jakub.personalrequestsservice.acceptation.infrastructure.AcceptationConfigurationRepository;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcceptationConfigurationService {

    private final AcceptationConfigurationRepository repository;

    public boolean confirmable(PersonalRequest request) {
        return repository.maxAcceptationLevel(request.getTenantId())
                .orElse(0)
                .equals(request.getAcceptationLevel());
    }



}
