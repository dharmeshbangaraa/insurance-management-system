package com.insuarance.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BroadcastMessage {

    @Autowired
    private EmailService emailService;

    private final Logger logger = LoggerFactory.getLogger(BroadcastMessage.class);

    @KafkaListener(topics = "broadcast-topic", groupId = "message-group")
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        logger.info("message consumed {}", consumerRecord.value());
        emailService.sendEmail("dpbangara@gmail.com", "Test Email", consumerRecord.value());
    }
}
