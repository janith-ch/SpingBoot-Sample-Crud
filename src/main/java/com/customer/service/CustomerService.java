package com.customer.service;

import com.customer.model.Customer;

import java.util.List;

public interface CustomerService {
    boolean createCustomer(Customer customer);

    List<Customer> getCustomers();

    boolean updateFirstName(String firstName);

    boolean deleteCustomerById(int id);
}
