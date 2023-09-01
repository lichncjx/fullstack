package com.onecool.fullstack;

import com.github.javafaker.Faker;
import com.onecool.fullstack.customer.Customer;
import com.onecool.fullstack.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FullStackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullStackApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(@Qualifier("jpa") CustomerRepository customers) {
        return args -> {

            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Customer customer = Customer.create(
                    firstName + " " + lastName,
                    firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com",
                    faker.number().numberBetween(18, 99));

            customers.insertCustomer(customer);
        };
    }
}
