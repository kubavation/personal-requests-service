package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRequestRepository extends JpaRepository<PersonalRequestEntity, Long> {
}
