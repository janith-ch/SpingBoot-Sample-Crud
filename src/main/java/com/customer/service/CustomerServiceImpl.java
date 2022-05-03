package com.customer.service;

import com.customer.model.Customer;
import com.customer.repository.CustomerRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepositoryImpl customerRepository;

    public CustomerServiceImpl(CustomerRepositoryImpl customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean createCustomer(Customer customer) {
        Number x =  customerRepository.createCustomer(customer);

        if (Integer.parseInt(String.valueOf(x)) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    @Override
    public boolean updateFirstName(String firstName) {
        return customerRepository.updateFirstName(firstName);
    }

    @Override
    public boolean deleteCustomerById(int id) {
        return customerRepository.deleteCustomerById(id);
    }
}
