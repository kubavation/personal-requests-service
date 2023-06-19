package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

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
public class PersonalRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Column(name = "tenant_id")
    private String tenantId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private Set<PersonalRequestFieldEntity> fields;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private Set<PersonalRequestAttachmentEntity> attachments;

    public void withAttachments(Set<PersonalRequestAttachmentEntity> attachments) {
      this.attachments = attachments.stream()
                .map(attachment -> {
                    attachment.setRequest(this);
                    return attachment;
                }).collect(Collectors.toSet());
    }

    public void withFields(Set<PersonalRequestFieldEntity> fields) {
        this.fields = fields.stream()
                .map(field -> {
                    field.setRequest(this);
                    return field;
                }).collect(Collectors.toSet());
    }

}
