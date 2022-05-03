package com.customer.repository;

import com.customer.model.Customer;

import java.util.List;

public interface CustomerRepository {

    Number createCustomer(Customer customer);

    List<Customer> getCustomers();

    boolean updateFirstName(String firstName);

    boolean deleteCustomerById(int id);
}
