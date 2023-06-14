package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "PR_PERSONAL_REQUEST")
@NoArgsConstructor
@Data
public class PersonalRequestEntity {

    @Id
    private Long id;

    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Column(name = "tenant_id")
    private String tenantId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "request")
    private Set<PersonalRequestFieldEntity> fields;
}
