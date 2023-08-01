package com.onecool.fullstack.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "customer_email_unique", columnNames = "email"))
public class Customer {

    @Id @GeneratedValue(generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String email;

    private Integer age;

    public static Customer create(String name, String email, Integer age){
        return new Customer(null, name, email, age);
    }
}
