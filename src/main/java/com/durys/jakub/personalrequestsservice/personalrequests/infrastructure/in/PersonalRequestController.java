package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in;

import com.durys.jakub.personalrequestsservice.personalrequests.application.PersonalRequestApplicationService;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/personal-requests")
@RequiredArgsConstructor
public class PersonalRequestController {

    private final PersonalRequestApplicationService personalRequestApplicationService;

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    void save(@RequestPart("personalRequest") PersonalRequest personalRequest,
              @RequestPart(name = "attachments", required = false) List<MultipartFile> attachments) {
        log.info(personalRequest.toString());
    }
}
