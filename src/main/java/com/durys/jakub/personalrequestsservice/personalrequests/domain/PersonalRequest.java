package com.durys.jakub.personalrequestsservice.personalrequests.domain;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.exception.StatusInvalidForOperationException;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "PR_PERSONAL_REQUEST")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PersonalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "supervisor_id")
    private String supervisorId;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "acceptation_level")
    private AcceptationLevel acceptationLevel;

    @Enumerated(EnumType.STRING)
    private PersonalRequestStatus status;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private Set<PersonalRequestField> fields;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private Set<PersonalRequestAttachment> attachments;

    public PersonalRequest withAttachments(Set<PersonalRequestAttachment> attachments) {
      this.attachments = attachments.stream()
              .map(attachment -> attachment.withRequest(this))
              .collect(Collectors.toSet());
      return this;
    }

    public PersonalRequest withFields(Set<PersonalRequestField> fields) {
        this.fields = fields.stream()
                .map(field -> field.withRequest(this))
                .collect(Collectors.toSet());
        return this;
    }

    public PersonalRequest withSupervisor(String supervisorId) {
        this.supervisorId = supervisorId;
        return this;
    }

    public PersonalRequest sendTo(String supervisorId) {

        if (status != PersonalRequestStatus.NEW && status != PersonalRequestStatus.REJECTED) {
            throw new StatusInvalidForOperationException();
        }

        this.supervisorId = supervisorId;
        status = PersonalRequestStatus.SENT_FOR_ACCEPTATION;
        acceptationLevel.increment();
        return this;
    }

    public PersonalRequest confirm() {

        if (status != PersonalRequestStatus.SENT_FOR_ACCEPTATION) {
            throw new StatusInvalidForOperationException();
        }

        status = PersonalRequestStatus.CONFIRMED;
        return this;
    }

    public PersonalRequest reject(String reason) {

        if (status != PersonalRequestStatus.SENT_FOR_ACCEPTATION) {
            throw new StatusInvalidForOperationException();
        }

        status = PersonalRequestStatus.REJECTED;
        rejectionReason = reason;
        acceptationLevel.reset();
        return this;
    }

    public Integer accaptationLevel() {
        return acceptationLevel.getLevel();
    }

}
