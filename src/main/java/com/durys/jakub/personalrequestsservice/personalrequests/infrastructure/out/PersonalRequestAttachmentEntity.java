package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.http.RequestEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Entity
@Table(name = "PR_PERSONAL_REQUEST_ATTACHMENT")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PersonalRequestAttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private PersonalRequestEntity request;

    @Column(name = "FILE_NAME")
    private String fileName;

    private byte[] file;

    @Enumerated(EnumType.STRING)
    private Status status;

    public PersonalRequestAttachmentEntity(MultipartFile file) {
        try {
            this.file = file.getBytes();
            this.fileName = file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException("Error in creating attachment");
        }
        this.status = Status.A;
    }

    public PersonalRequestAttachmentEntity withRequest(PersonalRequestEntity personalRequest) {
        this.request = personalRequest;
        return this;
    }
}
