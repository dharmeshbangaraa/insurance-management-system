package com.insurance.user.service;

import com.insurance.user.dto.LoginRequestDto;
import com.insurance.user.entity.Customer;

public interface CustomerService {
    Customer addNewCustomer(Customer customer);
    Customer login(LoginRequestDto loginRequestDto);
    Customer changePassword(Long userId, String password);
}
