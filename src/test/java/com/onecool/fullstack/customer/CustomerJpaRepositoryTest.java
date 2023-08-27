package com.onecool.fullstack.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CustomerJpaRepositoryTest {

    private CustomerJpaRepository underTestRepository;
    private AutoCloseable autoCloseable;
    @Mock
    private JpaCustomerRepository jpaRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTestRepository = new CustomerJpaRepository(jpaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
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