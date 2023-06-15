package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/personal-requests")
public interface PersonalRequestConstructOperations<T extends PersonalRequest> {

    @PostMapping
    void save(T t);

}
