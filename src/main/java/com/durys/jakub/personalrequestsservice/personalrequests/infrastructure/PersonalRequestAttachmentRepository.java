package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRequestAttachmentRepository extends JpaRepository<PersonalRequestAttachment, Long> {
}
