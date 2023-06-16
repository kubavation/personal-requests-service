package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in;

import com.durys.jakub.personalrequestsservice.personalrequests.application.PersonalRequestApplicationService;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.Request;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/personal-requests-details")
@RequiredArgsConstructor
public class PersonalRequestController {

    private final PersonalRequestApplicationService personalRequestApplicationService;

    @PostMapping
    void save(@RequestBody Request personalRequest) {
    }
}
