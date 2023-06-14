package com.durys.jakub.personalrequestsservice.personalrequests.domain.types;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.requestypes.domain.Type;

public abstract class RemoteWorkApplication extends PersonalRequest {
    protected abstract Type type();
}
