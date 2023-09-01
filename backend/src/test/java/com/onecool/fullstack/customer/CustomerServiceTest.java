package com.onecool.fullstack.customer;

import com.onecool.fullstack.exception.DuplicateResourceException;
import com.onecool.fullstack.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        String email = "alex@gmail.com";

        //when(customerRepository.existsByEmail(email)).thenReturn(false);

        RegisterCustomerRequest request = new RegisterCustomerRequest("Alex", email, 19);

        // When
        underTestService.register(request);

        // Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).insertCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getId()).isNull();
        assertThat(capturedCustomer.getName()).isEqualTo(request.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(request.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(request.age());
    }

    @Test
    void registerWillThrowWhenEmailExists() {
        // Given
        String email = "alex@gmail.com";

        when(customerRepository.existsByEmail(email)).thenReturn(true);

        RegisterCustomerRequest request = new RegisterCustomerRequest("Alex", email, 19);

        // When
        // Then
        assertThatThrownBy(() -> underTestService.register(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("email already taken");

        verify(customerRepository, never()).insertCustomer(any());
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
        long id = 1;

        // When
        underTestService.deleteCustomerById(id);

        // Then
        verify(customerRepository).deleteCustomerById(id);    }
}