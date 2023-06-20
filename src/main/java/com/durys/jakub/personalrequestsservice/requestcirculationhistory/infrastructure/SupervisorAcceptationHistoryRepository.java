package com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure;

import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.SupervisorAcceptationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorAcceptationHistoryRepository extends JpaRepository<SupervisorAcceptationHistory, Long> {
}
