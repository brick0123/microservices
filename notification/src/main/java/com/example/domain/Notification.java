package com.example.domain;

import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(schema = "notification")
public class Notification {

    protected Notification() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ReceiverInfo receiverInfo;

    private String sender;

    private String message;

    private LocalDate sendAt;

    @Builder
    public Notification(ReceiverInfo receiverInfo, String sender, String message, LocalDate sendAt) {
        this.receiverInfo = receiverInfo;
        this.sender = sender;
        this.message = message;
        this.sendAt = sendAt;
    }

    @Embeddable
    public static class ReceiverInfo {

        protected ReceiverInfo() {
        }

        private Long customerId;

        private String customerEmail;

        public ReceiverInfo(Long customerId, String customerEmail) {
            this.customerId = customerId;
            this.customerEmail = customerEmail;
        }
    }
}
