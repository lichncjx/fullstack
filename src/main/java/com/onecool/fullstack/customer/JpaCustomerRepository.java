package com.onecool.fullstack.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);
}
