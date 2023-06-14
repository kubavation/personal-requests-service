package com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test.requests;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.types.RemoteWorkApplication;
import com.durys.jakub.personalrequestsservice.requestypes.domain.Type;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TestRemoteWorkApplication extends RemoteWorkApplication {

    private LocalDate from;

    @Override
    public Type type() {
        return Type.REMOTE_WORK_APPLICATION;
    }
}
