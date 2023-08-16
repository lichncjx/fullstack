package com.onecool.fullstack.customer;

import com.onecool.fullstack.TestContainersUnitTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerJdbcRepositoryTest extends TestContainersUnitTestBase {

    private CustomerJdbcRepository repository;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        repository = new CustomerJdbcRepository(getJdbcTemplate(), customerRowMapper);
    }

    @Test
    void getAllCustomers() {
        // Given
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID(),
                20);
        repository.insertCustomer(customer);

        // When
        List<Customer> customers = repository.getAllCustomers();

        // Then
        assertThat(customers).isNotEmpty();
    }

    @Test
    void getCustomerById() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                email,
                20);
        repository.insertCustomer(customer);

        long id = repository.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        // When
        Optional<Customer> actual = repository.getCustomerById(id);

        // Then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void insertCustomer() {
        // Given

        // When

        // Then
    }

    @Test
    void updateCustomer() {
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

    @Test
    void existById() {
        // Given

        // When

        // Then
    }

    @Test
    void existByEmail() {
        // Given

        // When

        // Then
    }
}