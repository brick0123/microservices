package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqMessageProducer {

    private final static Logger log = LoggerFactory.getLogger(RabbitMqMessageProducer.class);

    private final AmqpTemplate amqpTemplate;

    public RabbitMqMessageProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publish(final String exchange, final String routingKey, final Object payload) {
        log.info(">>> Publishing to [{}] using routingKey [{}], payload [{}]", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info(">>> Published to [{}] using routingKey [{}], payload [{}]", exchange, routingKey, payload);
    }
}
