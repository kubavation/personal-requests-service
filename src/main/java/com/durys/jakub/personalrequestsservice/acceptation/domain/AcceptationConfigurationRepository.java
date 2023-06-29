package com.durys.jakub.personalrequestsservice.acceptation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface AcceptationConfigurationRepository extends JpaRepository<AcceptationConfiguration, Long> {

    @Query(value = "SELECT config.supervisor_id supervisorId from PR_ACCEPTATION_CONFIGURATION config " +
            " WHERE current_date between config.from and COALESCE(config.to, current_date) " +
            " AND config.level = :level AND config.tenant_id = :tenantId and config.status = 'A' ", nativeQuery = true)
    Optional<Supervisor> supervisor(String tenantId, Integer level);

    @Query(value = "SELECT max(config.level) from PR_ACCEPTATION_CONFIGURATION config " +
            " WHERE current_date between config.from and COALESCE(config.to, current_date) " +
            " AND config.tenant_id = :tenantId and config.status = 'A' ", nativeQuery = true)
    Optional<Integer> maxAcceptationLevel(String tenantId);
}
