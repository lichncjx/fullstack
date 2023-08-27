package com.onecool.fullstack.customer;

import com.onecool.fullstack.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService underTestService;
    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        underTestService = new CustomerService(customerRepository);
    }

    @Test
    void getAllCustomers() {
        // When
        underTestService.getAllCustomers();

        // Then
        verify(customerRepository).getAllCustomers();
    }

    @Test
    void canGetCustomerById() {
        // Given
        long id = 10;
        Customer customer = new Customer(id, "Ali", "ali@gmail.com", 20);

        when(customerRepository.getCustomerById(id)).thenReturn(Optional.of(customer));

        // When
        Customer actual = underTestService.getCustomerById(id);

        // Then
        assertThat(actual).isEqualTo(customer);
    }

    @Test
    void willThrowWhenGetCustomerByIdReturnEmptyOptional() {
        // Given
        long id = 10;

        when(customerRepository.getCustomerById(id)).thenReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> underTestService.getCustomerById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("customer with id [%s] not found".formatted(id));
    }

    @Test
    void register() {
        // Given

        // When

        // Then
    }

    @Test
    void update() {
        // Given

        // When

        // Then
    }

    @Test
    void deleteCustomerById() {
        // Given

        // When

        // Then
    }
}