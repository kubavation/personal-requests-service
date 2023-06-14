package com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test.requests;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.types.RemoteWorkApplication;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TestRemoteWorkApplication implements RemoteWorkApplication {
    private LocalDate from;
}
