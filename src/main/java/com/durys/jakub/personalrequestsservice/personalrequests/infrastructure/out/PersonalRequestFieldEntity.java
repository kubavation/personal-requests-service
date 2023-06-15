package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "PR_PERSONAL_REQUEST_FIELD")
@NoArgsConstructor
@Data
public class PersonalRequestFieldEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private PersonalRequestEntity request;

    @Column(name = "request_field_type_id")
    private Long requestFieldTypeId;

    @Column(name = "date_value")
    private LocalDate dateValue;

    @Column(name = "text_value")
    private String textValue;

    @Column(name = "int_value")
    private Long intValue;

    private String additional;

    @Enumerated(EnumType.STRING)
    private Status status;
}