package com.durys.jakub.personalrequestsservice.acceptation.infrastructure;

import com.durys.jakub.personalrequestsservice.acceptation.domain.AcceptationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcceptationConfigurationRepository extends JpaRepository<AcceptationConfiguration, Long> {
}
