package com.onecool.fullstack.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    Customer getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    void registerCustomer(@RequestBody RegisterCustomerRequest request){
        customerService.register(request);
    }

    @PutMapping("/{id}")
    void updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest request){
        customerService.update(id, request);
    }
}
