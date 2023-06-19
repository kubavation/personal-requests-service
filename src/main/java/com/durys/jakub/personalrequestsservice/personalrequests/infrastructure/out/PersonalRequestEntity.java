package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "PR_PERSONAL_REQUEST")
@NoArgsConstructor
@Data
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

    @OneToMany(mappedBy = "request")
    private Set<PersonalRequestFieldEntity> fields;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private Set<PersonalRequestAttachmentEntity> attachments;
}
