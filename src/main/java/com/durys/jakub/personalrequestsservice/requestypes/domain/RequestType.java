package com.durys.jakub.personalrequestsservice.requestypes.domain;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "PR_REQUEST_TYPE")
@NoArgsConstructor
@Data
public class RequestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;
    private String name;
    private String context;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "requestType")
    private Set<RequestTypeField> fields;
}
