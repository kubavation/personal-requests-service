package com.durys.jakub.personalrequestsservice.requestypes.infrastructure;

import com.durys.jakub.personalrequestsservice.requestypes.domain.RequestType;
import com.durys.jakub.personalrequestsservice.requestypes.domain.RequestTypeField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {

    @Query("select t from RequestTypeField t where t.requestType.id = :requestTypeId")
    Set<RequestTypeField> fields(Long requestTypeId);

}
