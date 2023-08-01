package com.onecool.fullstack.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    void insertCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomerById(Long id);
    boolean existById(Long id);
    boolean existByEmail(String email);
}
