package com.insurance.repository;

import com.insurance.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {

    @Query(value = "SELECT * FROM policy WHERE :userId = ANY(applied_user)", nativeQuery = true)
    List<Policy> findAllByAppliedUser(Long userId);
}
