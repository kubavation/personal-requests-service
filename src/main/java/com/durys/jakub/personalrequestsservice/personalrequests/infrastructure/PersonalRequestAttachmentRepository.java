package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PersonalRequestAttachmentRepository extends JpaRepository<PersonalRequestAttachment, Long> {

    @Query("from PersonalRequestAttachment where request.id = :requestId")
    Set<PersonalRequestAttachment> requestAttachments(Long requestId);
}
