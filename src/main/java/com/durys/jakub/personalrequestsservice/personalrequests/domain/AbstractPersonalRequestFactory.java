package com.durys.jakub.personalrequestsservice.personalrequests.domain;

import com.durys.jakub.personalrequestsservice.context.Context;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.contexts.test.TestPersonalRequestFactory;
import com.durys.jakub.personalrequestsservice.requestypes.domain.Type;
import com.durys.jakub.personalrequestsservice.requestypes.infrastructure.RequestTypeRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AbstractPersonalRequestFactory {

    public static Requestable instanceFrom(Context context, Type type, RequestTypeRepository repository) {
        return switch (context.name()) {
            case "TEST" -> new TestPersonalRequestFactory(repository).instanceOf(type);
            default -> throw new UnsupportedOperationException();
        };
    }
}
