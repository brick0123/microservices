package com.example.presentation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification", url = "${clients.notification.url}")
public interface NotificationApiCaller {

    @PostMapping("api/v1/notification")
    void sendNotification(NotificationRequest request);
}
