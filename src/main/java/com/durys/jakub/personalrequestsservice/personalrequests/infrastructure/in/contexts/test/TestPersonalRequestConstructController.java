package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in.contexts.test;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test.TestPersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in.PersonalRequestConstructOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@ConditionalOnProperty(prefix = "application", name = "context", havingValue = "TEST")
public class TestPersonalRequestConstructController implements PersonalRequestConstructOperations<TestPersonalRequest> {

    @Override
    public List<TestPersonalRequest> tenantRequests(String tenantId) {
        return List.of();
    }

    @Override
    public void save(@RequestBody TestPersonalRequest testPersonalRequest) {
        log.info("save {} ", testPersonalRequest);
    }
}
