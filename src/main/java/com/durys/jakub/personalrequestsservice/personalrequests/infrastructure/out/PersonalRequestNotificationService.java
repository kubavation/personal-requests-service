package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

import com.durys.jakub.notificationclient.api.client.NotificationClient;
import com.durys.jakub.notificationclient.api.model.Notification;
import com.durys.jakub.notificationclient.api.model.NotificationType;
import com.durys.jakub.notificationclient.api.model.TenantId;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequest;
import com.durys.jakub.personalrequestsservice.personalrequests.domain.PersonalRequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonalRequestNotificationService {
    
    private final NotificationClient notificationClient;

    public void notify(PersonalRequest request) {
        notificationClient.send(
                new Notification(
                        new TenantId(receiver(request)),
                        content(request),
                        content(request),
                        availableChannels()));
    }

    public void notify(Collection<PersonalRequest> requests) {
        requests.stream()
                .forEach(this::notify);
    }

    private static String content(PersonalRequest request) {
        return switch (request.getStatus()) {
            case REJECTED -> PersonalRequestNotification.REJECTION.content().formatted(request.getId());
            case CONFIRMED -> PersonalRequestNotification.CONFIRMATION.content().formatted(request.getId());
            case SENT_FOR_ACCEPTATION -> PersonalRequestNotification.SENT_FOR_ACCEPTATION.content().formatted(request.getId());
            default -> null;
        };
    }

    private static String receiver(PersonalRequest request) {
        if (request.getStatus().equals(PersonalRequestStatus.SENT_FOR_ACCEPTATION)) {
            return request.getSupervisorId();
        }
        return request.getTenantId();
    }

    private static List<NotificationType> availableChannels() {
        return List.of(NotificationType.APP, NotificationType.EMAIL);
    }

}
