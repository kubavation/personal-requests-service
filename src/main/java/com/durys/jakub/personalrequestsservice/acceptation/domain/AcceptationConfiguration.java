package com.durys.jakub.personalrequestsservice.acceptation.domain;


import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PR_ACCEPTATION_CONFIGURATION")
public class AcceptationConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TENANT_ID")
    private String tenantId;

    @Column(name = "SUPERVISOR_ID")
    private String supervisorId;

    private LocalDate from;

    private LocalDate to;

    private Integer level;

    @Enumerated(EnumType.STRING)
    private Status status;
}
