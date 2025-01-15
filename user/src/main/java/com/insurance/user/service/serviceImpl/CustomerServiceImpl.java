package com.insurance.user.service.serviceImpl;

import com.insurance.user.dto.LoginRequestDto;
import com.insurance.user.entity.Customer;
import com.insurance.user.repository.CustomerRepository;
import com.insurance.user.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.enabled}")
    private boolean isKafkaEnabled;

    @Override
    public Customer addNewCustomer(Customer customer) {
        log.info("message publishing to topic");
        if(isKafkaEnabled) kafkaTemplate.send("broadcast-topic", customer);
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer login(LoginRequestDto loginRequestDto) {
        Customer loginCustomer = this.customerRepository.findByEmailId(loginRequestDto.emailId()).orElse(null);
        log.info("login user details: {}", loginCustomer);
        if(Objects.nonNull(loginCustomer)) {
            if(loginCustomer.getPassword().equals(loginRequestDto.password())) {
                return loginCustomer;
            }
            return null;
        }
        return null;
    }

    @Override
    public Customer changePassword(Long userId, String password) {
        Customer customer = this.customerRepository.findById(userId).orElse(null);
        if(Objects.nonNull(customer)) {
            customer.setPassword(password);
            customerRepository.save(customer);
        }
        else {
            log.info("Customer not found with userId: {}", userId);
        }
        return customer;
    }
}
