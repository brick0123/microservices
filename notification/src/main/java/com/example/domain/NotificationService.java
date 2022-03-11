package com.example.domain;

import com.example.domain.Notification.ReceiverInfo;
import com.example.presentation.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(final NotificationRequest request) {
        final Notification entity = Notification.builder()
            .receiverInfo(new ReceiverInfo(request.customerId(), request.customerEmail()))
            .message(request.message())
            .sendAt(request.sendAt())
            .build();

        notificationRepository.save(entity);
    }

}
