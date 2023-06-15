package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.Requestable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/personal-requests")
public interface PersonalRequestConstructOperations<T extends Requestable> {

    @GetMapping
    List<T> tenantRequests(String tenantId);

    @PostMapping
    void save(T t);

}
