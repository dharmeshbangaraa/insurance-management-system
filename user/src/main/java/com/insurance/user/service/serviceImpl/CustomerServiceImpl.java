package com.insurance.user.service.serviceImpl;

import com.insurance.user.entity.Customer;
import com.insurance.user.repository.CustomerRepository;
import com.insurance.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addNewCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }
}
