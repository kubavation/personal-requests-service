package com.durys.jakub.personalrequestsservice.requestcirculationhistory.infrastructure;

import com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain.SupervisorAcceptationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SupervisorAcceptationHistoryRepository extends JpaRepository<SupervisorAcceptationHistory, Long> {

    @Query("from SupervisorAcceptationHistory h where h.requestId = :requestId and h.supervisorId = :supervisorId")
    Optional<SupervisorAcceptationHistory> findBySupervisorAndRequest(String supervisorId, String requestId);

    List<SupervisorAcceptationHistory> findByRequestId(Long requestId);
}
