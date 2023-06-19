package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure;

import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out.PersonalRequestAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRequestAttachmentRepository extends JpaRepository<PersonalRequestAttachmentEntity, Long> {
}
