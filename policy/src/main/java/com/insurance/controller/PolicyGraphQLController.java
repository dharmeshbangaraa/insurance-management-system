package com.insurance.controller;

import com.insurance.entity.Policy;
import com.insurance.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PolicyGraphQLController {

    @Autowired
    private PolicyService policyService;

    @QueryMapping
    public List<Policy> fetchPolicies(@Argument Long userId) {
        if(userId == null) {
            return this.policyService.getAll();
        }
        return this.policyService.getUserEnrolledPolicies(userId);
    }
}
