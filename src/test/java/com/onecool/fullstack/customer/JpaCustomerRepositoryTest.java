package com.onecool.fullstack.customer;

import com.onecool.fullstack.TestContainersUnitTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaCustomerRepositoryTest extends TestContainersUnitTestBase {

    @Autowired
    private JpaCustomerRepository underTestRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void existsByEmail() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = Customer.create(
                FAKER.name().fullName(),
                email,
                20);
        underTestRepository.save(customer);

        // When
        boolean actual = underTestRepository.existsByEmail(email);

        // Then
        assertThat(actual).isTrue();
    }

    @Test
    void existsByEmailFailsWhenEmailNotExist() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();

        // When
        boolean actual = underTestRepository.existsByEmail(email);

        // Then
        assertThat(actual).isFalse();
    }
}