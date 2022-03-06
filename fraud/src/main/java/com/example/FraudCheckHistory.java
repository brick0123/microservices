package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;

@Entity
public class FraudCheckHistory extends BaseEntity {

    protected FraudCheckHistory() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private boolean isFraudster;

    @Builder
    public FraudCheckHistory(Long customerId, boolean isFraudster) {
        this.customerId = customerId;
        this.isFraudster = isFraudster;
    }
}
