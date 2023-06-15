package com.durys.jakub.personalrequestsservice.personalrequests.domain;

import com.durys.jakub.personalrequestsservice.requestypes.domain.Type;

public interface PersonalRequestFactory {
    Requestable instanceOf(Type type);
}
