package com.insuarance.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuarance.dto.CustomerDto;
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
    private final ObjectMapper jsonObjectMapper = new ObjectMapper();

    @KafkaListener(topics = "broadcast-topic", groupId = "message-group")
    public void receive(ConsumerRecord<String, Object> consumerRecord) throws JsonProcessingException {
        CustomerDto customerDto = jsonObjectMapper.readValue(consumerRecord.value().toString(), CustomerDto.class);
        logger.info("message consumed {}", customerDto);
        emailService.sendEmail(customerDto.emailId(), "Test Email", String.format("Congratulations %s!!! Your account has been created. Thank you.", customerDto.firstName()));
    }
}
