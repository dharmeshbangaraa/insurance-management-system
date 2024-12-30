package com.insurance.user.service.serviceImpl;

import com.insurance.user.entity.Customer;
import com.insurance.user.repository.CustomerRepository;
import com.insurance.user.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public Customer addNewCustomer(Customer customer) {
        log.info("message published to topic");
        kafkaTemplate.send("broadcast-topic", customer);
        return this.customerRepository.save(customer);
    }
}
