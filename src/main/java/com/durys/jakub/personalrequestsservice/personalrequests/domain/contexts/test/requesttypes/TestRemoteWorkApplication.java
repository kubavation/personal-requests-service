package com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test.requesttypes;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test.TestPersonalRequest;
import com.durys.jakub.personalrequestsservice.requestypes.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class TestRemoteWorkApplication extends TestPersonalRequest {

    private LocalDate from;


    public Type type() {
        return Type.REMOTE_WORK_APPLICATION;
    }
}
