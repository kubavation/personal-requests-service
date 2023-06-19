package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

import com.durys.jakub.personalrequestsservice.shared.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.RequestEntity;

import java.util.Set;

@Entity
@Table(name = "PR_PERSONAL_REQUEST_ATTACHMENT")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PersonalRequestAttachmentEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private PersonalRequestEntity request;

    @Column(name = "FILE_NAME")
    private String fileName;

    private byte[] file;

    @Enumerated(EnumType.STRING)
    private Status status;

}
