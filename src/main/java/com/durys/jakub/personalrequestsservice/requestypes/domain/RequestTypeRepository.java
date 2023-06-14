package com.durys.jakub.personalrequestsservice.requestypes.domain;

import java.util.Optional;
import java.util.Set;

public interface RequestTypeRepository {
    Optional<RequestType> load(Long id);
    Set<RequestTypeField> requestFields(Long id);
}
