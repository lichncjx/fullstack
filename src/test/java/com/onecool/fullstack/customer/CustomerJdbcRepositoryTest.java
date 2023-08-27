package com.onecool.fullstack.customer;

import com.onecool.fullstack.TestContainersUnitTestBase;
import org.junit.jupiter.api.BeforeEach;
import  org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerJdbcRepositoryTest extends TestContainersUnitTestBase {

    private CustomerJdbcRepository underTestRepository;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        underTestRepository = new CustomerJdbcRepository(getJdbcTemplate(), customerRowMapper);
    }

    @Test
    void getAllCustomers() {
        // Given
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID(),
                20);
        underTestRepository.insertCustomer(customer);

        // When
        List<Customer> customers = underTestRepository.getAllCustomers();

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
        underTestRepository.insertCustomer(customer);

        long id = underTestRepository.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        // When
        Optional<Customer> actual = underTestRepository.getCustomerById(id);

        // Then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void willReturnEmptyWhenGetCustomerByNonExistingId(){
        // Given
        long id = -1;

        // When
        Optional<Customer> actual = underTestRepository.getCustomerById(id);

        // Then
        assertThat(actual).isEmpty();
    }

    @Test
    void insertCustomer() {
        // Given

        // When

        // Then
    }

    @Test
    void updateCustomerName() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                email,
                20);
        underTestRepository.insertCustomer(customer);

        long id = underTestRepository.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newName = "foo";

        // When
        Customer update = new Customer();
        update.setId(id);
        update.setName(newName);

        underTestRepository.updateCustomer(update);

        // Then
        Optional<Customer> actual = underTestRepository.getCustomerById(id);
        assertThat(actual).isPresent().hasValueSatisfying(c -> assertThat(c.getName()).isEqualTo(newName));
    }

    @Test
    void updateCustomerEmail() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                email,
                20);
        underTestRepository.insertCustomer(customer);

        long id = underTestRepository.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newEmail = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();

        // When
        Customer update = new Customer();
        update.setId(id);
        update.setEmail(newEmail);

        underTestRepository.updateCustomer(update);

        // Then
        Optional<Customer> actual = underTestRepository.getCustomerById(id);
        assertThat(actual).isPresent().hasValueSatisfying(c -> assertThat(c.getEmail()).isEqualTo(newEmail));
    }

    @Test
    void updateCustomerAge() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                email,
                20);
        underTestRepository.insertCustomer(customer);

        long id = underTestRepository.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newAge = 100;

        // When
        Customer update = new Customer();
        update.setId(id);
        update.setAge(newAge);

        underTestRepository.updateCustomer(update);

        // Then
        Optional<Customer> actual = underTestRepository.getCustomerById(id);
        assertThat(actual).isPresent().hasValueSatisfying(c -> assertThat(c.getAge()).isEqualTo(newAge));
    }

    @Test
    void updateAllPropertiesCustomer(){
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                email,
                20);
        underTestRepository.insertCustomer(customer);

        long id = underTestRepository.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newName = "foo";
        var newEmail = UUID.randomUUID().toString();
        var newAge = 100;

        // When
        Customer update = new Customer();
        update.setId(id);
        update.setName(newName);
        update.setEmail(newEmail);
        update.setAge(newAge);

        underTestRepository.updateCustomer(update);

        // Then
        Optional<Customer> actual = underTestRepository.getCustomerById(id);
        assertThat(actual).isPresent().hasValue(update);
    }

    @Test
    void deleteCustomerById() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                email,
                20);
        underTestRepository.insertCustomer(customer);

        long id = underTestRepository.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        // When
        underTestRepository.deleteCustomerById(id);

        // Then
        Optional<Customer> actual = underTestRepository.getCustomerById(id);
        assertThat(actual).isEmpty();
    }

    @Test
    void existById() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                email,
                20);
        underTestRepository.insertCustomer(customer);

        long id = underTestRepository.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        // When
        boolean actual = underTestRepository.existsById(id);

        // Then
        assertThat(actual).isTrue();
    }

    @Test
    void returnFalseWhenCustomerWithGivenIdDoesNotExist(){
        // Given
        long id = -1;

        // When
        boolean actual = underTestRepository.existsById(id);

        // Then
        assertThat(actual).isFalse();
    }

    @Test
    void existByEmail() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        String name = FAKER.name().fullName();
        Customer customer = Customer.create(name, email, 20);
        underTestRepository.insertCustomer(customer);

        // When
        boolean actual = underTestRepository.existsByEmail(email);

        // Then
        assertThat(actual).isTrue();
    }

    @Test
    void returnFalseWhenCustomerWithGivenEmailDoesNotExist(){
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();

        // When
        boolean actual = underTestRepository.existsByEmail(email);

        // Then
        assertThat(actual).isFalse();
    }
}