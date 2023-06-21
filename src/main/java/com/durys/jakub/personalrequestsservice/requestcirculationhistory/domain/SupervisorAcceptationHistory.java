package com.durys.jakub.personalrequestsservice.requestcirculationhistory.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PR_SUPERVISOR_ACCEPTATION_HISTORY")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class SupervisorAcceptationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SUPERVISOR_ID")
    private String supervisorId;

    @Column(name = "REQUEST_ID")
    private Long requestId;

    @Column(name = "REQUEST_HISTORY")
    private String requestHistory;


    public SupervisorAcceptationHistory withHistory(String history) {
        this.requestHistory = history;
        return this;
    }
}
