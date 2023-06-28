package com.durys.jakub.personalrequestsservice.acceptation.infrastructure;

import com.durys.jakub.personalrequestsservice.acceptation.domain.AcceptationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AcceptationConfigurationRepository extends JpaRepository<AcceptationConfiguration, Long> {

    @Query(value = "SELECT config.supervisor_id supervisorId from PR_ACCEPTATION_CONFIGURATION config " +
            " WHERE current_date between config.from and COALESCE(config.to, current_date) " +
            " AND config.level = :level AND config.tenant_id = :tenantId and config.status = 'A' ", nativeQuery = true)
    Optional<AcceptationConfiguration> supervisor(String tenantId, Integer level);
}
