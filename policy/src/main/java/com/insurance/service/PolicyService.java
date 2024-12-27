package com.insurance.service;

import com.insurance.entity.Policy;

import java.util.List;

public interface PolicyService {
    Policy newPolicy(Policy policy);

    List<Policy> getAll();

    String deleteById(String policyId);

    List<Policy> getUserEnrolledPolicies(Long userId);

    Policy addPolicyToUser(Long userId, String policyId);
}
