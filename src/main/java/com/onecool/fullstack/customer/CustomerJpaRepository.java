package com.onecool.fullstack.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository("jpa")
public class CustomerJpaRepository implements CustomerRepository {

    private final JpaCustomerRepository jpaRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void insertCustomer(Customer customer) {
        jpaRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jpaRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
