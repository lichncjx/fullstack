package com.onecool.fullstack.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository("jdbc")
public class CustomerJdbcRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    @Override
    public List<Customer> getAllCustomers() {
        var sql = """
                select id, name, email, age
                from customer
                """;

        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        var sql = """
                select id, name, email, age
                from customer
                where id = ?
                limit 1
                """;

        return jdbcTemplate.query(sql, customerRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        var sql = """
                insert into customer(name, email, age)
                values (?, ?, ?)
                """;
        int update = jdbcTemplate.update(sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAge());

        log.info("jdbcTemplate.update " + update);
    }

    @Override
    public void updateCustomer(Customer customer) {
        var sql = """
                update customer
                set name = ?, email = ?, age = ?
                where id = ?
                """;
        int update = jdbcTemplate.update(sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAge(),
                customer.getId());

        log.info("jdbcTemplate.update " + update);
    }

    @Override
    public void deleteCustomerById(Long id) {
        var sql = """
                delete
                from customer
                where id = ?
                """;
        int update = jdbcTemplate.update(sql, id);
        log.info("deleteCustomerById result = " + update);
    }

    @Override
    public boolean existsById(Long id) {
        var sql = """
                select count(*)
                from customer
                where id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        var sql = """
                select count(*)
                from customer
                where email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}
