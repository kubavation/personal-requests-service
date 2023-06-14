package com.durys.jakub.personalrequestsservice.requestypes.domain;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PR_REQUEST_TYPE")
@NoArgsConstructor
@Data
public class RequestType {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;
    private String name;
    private String context;

    @Enumerated(EnumType.STRING)
    private Status status;
}
