package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.in;

import com.durys.jakub.personalrequestsservice.personalrequests.application.PersonalRequestApplicationService;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.PersonalRequestAttachmentRepository;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequestDTO;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestAttachment;
import com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.model.PersonalRequestRejectionReason;
import com.durys.jakub.personalrequestsservice.shared.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    void save(@RequestPart("personalRequest") PersonalRequestDTO personalRequestDTO,
              @RequestPart(name = "attachments", required = false) List<MultipartFile> attachments) {
        log.info(personalRequestDTO.toString());
        personalRequestApplicationService.save(personalRequestDTO, attachments);
    }

    @PatchMapping("/confirmation")
    void confirm(@RequestBody Set<Long> requestIds) {
        personalRequestApplicationService.confirm(requestIds);
    }

    @PatchMapping("/rejection")
    void reject(@RequestBody Set<PersonalRequestRejectionReason> rejectionReasons) {
        personalRequestApplicationService.reject(rejectionReasons);
    }

    @GetMapping("{requestId}/attachments/{attachmentId}")
    ResponseEntity<Resource> downloadAttachment(@PathVariable Long attachmentId) {

        PersonalRequestAttachment attachment = personalRequestAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new EntityNotFoundException(PersonalRequestAttachment.class, attachmentId));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(attachment.getFile().length)
                .body(new InputStreamResource(new ByteArrayInputStream(attachment.getFile())));
    }

    @GetMapping("{requestId}/attachments")
    void downloadAttachments(@PathVariable Long requestId, HttpServletResponse response) {


        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {

            personalRequestAttachmentRepository.requestAttachments(requestId)
                    .stream()
                    .forEach(attachment -> {
                        try {
                            ZipEntry entry = new ZipEntry(attachment.getFileName());
                            entry.setSize(attachment.getFile().length);
                            zipOut.putNextEntry(entry);
                            StreamUtils.copy(attachment.getFile(), zipOut);
                            zipOut.closeEntry();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            zipOut.finish();
            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"attachments.zip\"");

        } catch (Exception ex) {
            log.error("error while downloading attachments", ex);
        }
    }
}

