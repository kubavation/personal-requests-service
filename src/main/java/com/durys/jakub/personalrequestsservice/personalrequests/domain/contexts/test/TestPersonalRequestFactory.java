package com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.Requestable;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestFactory;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test.requesttypes.TestRemoteWorkApplication;
import com.durys.jakub.personalrequestsservice.requestypes.domain.Type;
import com.durys.jakub.personalrequestsservice.requestypes.infrastructure.RequestTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestPersonalRequestFactory implements PersonalRequestFactory {

    private final RequestTypeRepository repository;

    @Override
    public Requestable instanceOf(Type type) {
//       return switch (type) {
//           case REMOTE_WORK_APPLICATION -> createRemoteWorkApplication(repository);
//       };
        return null;
    }

    private TestRemoteWorkApplication createRemoteWorkApplication(RequestTypeRepository repository) {
        return new TestRemoteWorkApplication();
    }
}
