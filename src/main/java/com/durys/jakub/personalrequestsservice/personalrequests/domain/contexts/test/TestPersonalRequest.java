package com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.TestRequestConfiguration;
import lombok.Data;

@TestRequestConfiguration
@Data
public abstract class TestPersonalRequest implements PersonalRequest {
}
