package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in;

import com.durys.jakub.personalrequestsservice.personalrequests.application.PersonalRequestApplicationService;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.PersonalRequestAttachmentRepository;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out.PersonalRequestAttachmentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/personal-requests")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class PersonalRequestController {

    private final PersonalRequestApplicationService personalRequestApplicationService;
    private final PersonalRequestAttachmentRepository personalRequestAttachmentRepository;

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    void save(@RequestPart("personalRequest") PersonalRequest personalRequest,
              @RequestPart(name = "attachments", required = false) List<MultipartFile> attachments) {
        log.info(personalRequest.toString());
        personalRequestApplicationService.save(personalRequest, attachments);
    }

    @GetMapping("{requestId}/attachments/{attachmentId}")
    ResponseEntity<Resource> downloadAttachment(@PathVariable Long attachmentId) {

        PersonalRequestAttachmentEntity attachment = personalRequestAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("entity not found"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(attachment.getFile().length)
                .body(new InputStreamResource(new ByteArrayInputStream(attachment.getFile())));
    }
}

