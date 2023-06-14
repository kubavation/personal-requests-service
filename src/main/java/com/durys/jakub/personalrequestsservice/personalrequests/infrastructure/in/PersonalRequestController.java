package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in;

import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.requestypes.domain.Type;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/personal-requests")
@RequiredArgsConstructor
public class PersonalRequestController {


    @GetMapping("/{type}/pattern")
    PersonalRequest pattern(@PathVariable Type type) {
        log.info("pattern for {}", type.name());
        return null;
    }
}
