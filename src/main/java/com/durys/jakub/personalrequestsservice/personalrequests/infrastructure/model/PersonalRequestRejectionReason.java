package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PersonalRequestRejectionReason {
    private Long requestId;
    private String reason;
}
