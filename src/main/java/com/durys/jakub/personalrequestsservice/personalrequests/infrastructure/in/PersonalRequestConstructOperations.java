package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.Requestable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/personal-requests")
public interface PersonalRequestConstructOperations<T extends Requestable> {

    @PostMapping
    void save(T t);

}
