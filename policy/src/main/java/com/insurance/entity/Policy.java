package com.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Policy {
    @Id
    @UuidGenerator
    private String id;
    private String policyName;
    private String category;
    private String company;
    private String cover;
    private Long totalPremium;
    private Long duration;
    private double claimPercent;
    private List<Long> appliedUser = new ArrayList<>();
}
