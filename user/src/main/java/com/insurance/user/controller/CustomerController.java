package com.insurance.user.controller;

import com.insurance.user.dto.LoginRequestDto;
import com.insurance.user.entity.Customer;
import com.insurance.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer) {
        return this.customerService.addNewCustomer(customer);
    }

    @PostMapping("/login")
    public Customer login(@RequestBody LoginRequestDto loginRequestDto) {
        return this.customerService.login(loginRequestDto);
    }
}
