package com.durys.jakub.personalrequestsservice.personalrequests.infrastructure.out;

import com.durys.jakub.notificationclient.api.client.NotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalRequestNotificationService {

    private final NotificationClient notificationClient;
}
