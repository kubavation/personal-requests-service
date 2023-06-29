package com.durys.jakub.personalrequestsservice.acceptation.domain;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestStatus;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcceptationConfigurationService {

    private final AcceptationConfigurationRepository repository;


    public Either<AcceptationResult, Supervisor> supervisor(PersonalRequest request) {

        if (confirmable(request)) {
            return PersonalRequestStatus.NEW.equals(request.getStatus())
                    ? Either.left(AcceptationResult.SUPERVISOR_NOT_DEFINED)
                    : Either.left(AcceptationResult.ACCEPTABLE);
        }

        return repository.supervisor(request.getTenantId(), request.getAcceptationLevel() + 1)
                .<Either<AcceptationResult, Supervisor>>map(Either::right)
                .orElseGet(() -> Either.left(AcceptationResult.SUPERVISOR_NOT_DEFINED));
    }


    private boolean confirmable(PersonalRequest request) {
        return repository.maxAcceptationLevel(request.getTenantId())
                .orElse(0)
                .equals(request.getAcceptationLevel());
    }


}
