package com.insurance.user.repository;

import com.insurance.user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM CUSTOMER WHERE email_id=:emailId", nativeQuery = true)
    Optional<Customer> findByEmailId(String emailId);

}
