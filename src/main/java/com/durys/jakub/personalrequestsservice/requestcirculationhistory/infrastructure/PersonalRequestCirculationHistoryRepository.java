package com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure;

import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.PersonalRequestCirculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRequestCirculationHistoryRepository extends JpaRepository<PersonalRequestCirculationHistory, Long> {
}
