package com.durys.jakub.personalrequestsservice.personalrequests.domain;

import com.durys.jakub.personalrequestsservice.shared.Status;
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

    @Enumerated(EnumType.STRING)
    private Status status;

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

}
