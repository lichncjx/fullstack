package com.onecool.fullstack.customer;

import com.onecool.fullstack.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(@Qualifier("jdbc") CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAllCustomers() {
        return repository.getAllCustomers();
    }

    public Customer getCustomerById(Long id) {
        return repository.getCustomerById(id).orElseThrow();
    }

    public void register(RegisterCustomerRequest request) {
        if(repository.existsByEmail(request.email())) return;

        repository.insertCustomer(Customer.create(request.name(), request.email(), request.age()));
    }

    public void update(Long id, UpdateCustomerRequest request) {

        Customer customer = getCustomerById(id);

        boolean change = false;
        if (request.name() != null && !request.name().equals(customer.getName())){
            customer.setName(request.name());
            change = true;
        }

        if (request.age() != null && !request.age().equals(customer.getAge())){
            customer.setAge(request.age());
            change = true;
        }

        if (request.email() != null && !request.email().equals(customer.getEmail())){
            if (repository.existsByEmail(request.email())){
                throw new DuplicateResourceException("email already taken");
            }
            customer.setEmail(request.email());
            change = true;
        }

        if (change)
            repository.updateCustomer(customer);
    }
}
