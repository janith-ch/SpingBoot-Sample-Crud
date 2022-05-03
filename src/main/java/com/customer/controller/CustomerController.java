package com.customer.controller;


import com.customer.model.Customer;
import com.customer.service.CustomerServiceImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {


    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public boolean createCustomer(@RequestBody Customer customer) {
        log.info("request data from client {} ", customer);
        return customerService.createCustomer(customer);
    }

    @GetMapping("/")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PutMapping
    public boolean updateFirstName(@RequestParam String firstName){
        return customerService.updateFirstName(firstName);

    }

    @DeleteMapping("/{id}")
    public boolean updateFirstName(@PathVariable int id){
        return customerService.deleteCustomerById(id);

    }

}
