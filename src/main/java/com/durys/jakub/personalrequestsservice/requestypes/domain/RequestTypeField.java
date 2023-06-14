package com.durys.jakub.personalrequestsservice.requestypes.domain;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PR_REQUEST_TYPE_FIELD")
@NoArgsConstructor
@Data
public class RequestTypeField {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_type_id")
    private RequestType requestType;

    private String name;
    private String context;

    @Enumerated(EnumType.STRING)
    private FieldType type;

    @Enumerated(EnumType.STRING)
    private Status status;
}
