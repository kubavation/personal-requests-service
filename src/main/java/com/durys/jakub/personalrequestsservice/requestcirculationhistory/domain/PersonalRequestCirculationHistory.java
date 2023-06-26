package com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PR_PERSONAL_REQUEST_HISTORY")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PersonalRequestCirculationHistory {

    @Id
    @Column(name = "REQUEST_ID")
    private Long requestId;

    @Column(name = "TENANT_ID")
    private String tenantId;

    private String history;


    public PersonalRequestCirculationHistory withHistory(String history) {
        this.history = history;
        return this;
    }

}
