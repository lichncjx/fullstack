package com.onecool.fullstack.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerJpaRepositoryTest {

    private CustomerJpaRepository underTestRepository;
    @Mock
    private JpaCustomerRepository jpaRepository;

    @BeforeEach
    void setUp() {
        underTestRepository = new CustomerJpaRepository(jpaRepository);
    }

    @Test
    void getAllCustomers() {
        // When
        underTestRepository.getAllCustomers();

        // Then
        verify(jpaRepository).findAll();
    }

    @Test
    void getCustomerById() {
        // Given
        long id = 1;

        // When
        underTestRepository.getCustomerById(id);

        // Then
        verify(jpaRepository).findById(id);
    }

    @Test
    void insertCustomer() {
        // Given
        Customer customer = new Customer(1L, "Ali", "ali@gmail.com", 2);

        // When
        underTestRepository.insertCustomer(customer);

        // Then
        verify(jpaRepository).save(customer);
    }

    @Test
    void updateCustomer() {
        // Given
        Customer customer = new Customer(1L, "Ali", "ali@gmail.com", 2);

        // When
        underTestRepository.updateCustomer(customer);

        // Then
        verify(jpaRepository).save(customer);
    }

    @Test
    void deleteCustomerById() {
        // Given
        long id = 1;

        // When
        underTestRepository.deleteCustomerById(id);

        // Then
        verify(jpaRepository).deleteById(id);
    }

    @Test
    void existsById() {
        // Given
        long id = 1;

        // When
        underTestRepository.existsById(id);

        // Then
        verify(jpaRepository).existsById(id);
    }

    @Test
    void existsByEmail() {
        // Given
        String email = "foo@gmail.com";

        // When
        underTestRepository.existsByEmail(email);

        // Then
        verify(jpaRepository).existsByEmail(email);
    }
}