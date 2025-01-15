package com.insurance.service.serviceImpl;

import com.insurance.entity.Policy;
import com.insurance.repository.PolicyRepository;
import com.insurance.service.PolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public Policy newPolicy(Policy policy) {
        return this.policyRepository.save(policy);
    }

    @Override
    public List<Policy> getAll() {
        return this.policyRepository.findAll();
    }

    @Override
    public String deleteById(String policyId) {
        Policy policy = this.policyRepository.findById(policyId).orElseThrow(RuntimeException::new);
        return policy.getId();
    }

    @Override
    public List<Policy> getUserEnrolledPolicies(Long userId) {
        return this.policyRepository.findAllByAppliedUser(userId);
    }

    @Override
    public Policy addPolicyToUser(Long userId, String policyId) {
        Policy policy = this.policyRepository.findById(policyId).orElseThrow(()-> new RuntimeException("No Policy Id found"));
        log.info("Found policy {}", policy.toString());
        if(policy.getAppliedUser().contains(userId)) {
            log.info("user already applied to policy {}", policyId);
            return null;
        }
        policy.getAppliedUser().add(userId);
        return this.policyRepository.save(policy);
    }

    @Override
    public Policy getPolicyById(String policyId) {
        return this.policyRepository.findById(policyId).orElse(null);
    }
}
