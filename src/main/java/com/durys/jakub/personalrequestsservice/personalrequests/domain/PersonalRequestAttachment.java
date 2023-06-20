package com.durys.jakub.personalrequestsservice.personalrequests.domain;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@Table(name = "PR_PERSONAL_REQUEST_ATTACHMENT")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PersonalRequestAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private PersonalRequest request;

    @Column(name = "FILE_NAME")
    private String fileName;

    private byte[] file;

    @Enumerated(EnumType.STRING)
    private Status status;

    public PersonalRequestAttachment(MultipartFile file) {
        try {
            this.file = file.getBytes();
            this.fileName = file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException("Error in creating attachment");
        }
        this.status = Status.A;
    }

    public PersonalRequestAttachment withRequest(PersonalRequest personalRequest) {
        this.request = personalRequest;
        return this;
    }
}
