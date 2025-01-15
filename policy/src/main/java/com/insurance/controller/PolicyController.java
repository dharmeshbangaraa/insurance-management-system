package com.insurance.controller;

import com.insurance.entity.Policy;
import com.insurance.service.serviceImpl.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {

    @Autowired
    private PolicyServiceImpl policyService;

    @PostMapping("/")
    public ResponseEntity<Policy> create(@RequestBody Policy policy) {
        return ResponseEntity.ok(policyService.newPolicy(policy));
    }

    @GetMapping("/")
    public ResponseEntity<List<Policy>> allPolicies() {
        return ResponseEntity.ok(policyService.getAll());
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deletePolicy(@RequestParam String policyId) {
        return ResponseEntity.ok(String.format("Policy deleted with Id: %s", this.policyService.deleteById(policyId)));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Policy>> userEnrolledPolicies(@RequestParam Long userId) {
        return ResponseEntity.ok(this.policyService.getUserEnrolledPolicies(userId));
    }

    @PostMapping("")
    public ResponseEntity<Policy> enrollUser(@RequestParam String policyId, @RequestParam Long userId) {
        Policy policy = this.policyService.addPolicyToUser(userId, policyId);
        if(Objects.nonNull(policy)) {
            return ResponseEntity.ok(policy);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @GetMapping("/")
//    public ResponseEntity<Policy> getUserByPolicyId(@RequestParam Long userId, @RequestParam String policyId) {
//        // TODO by Ashish;
//        return null;
//    }
}
