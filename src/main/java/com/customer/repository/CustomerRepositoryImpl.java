package com.customer.repository;

import com.customer.mapper.CustomerMapper;
import com.customer.model.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public JdbcTemplate jdbcTemplate;

    public CustomerRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Number createCustomer(Customer customer) {
        StringBuilder query = new StringBuilder("INSERT INTO customer(first_name,last_name,age,salary) VALUES(?,?,?,?)");
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> {
                final PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, customer.getFirstName());
                ps.setString(2, customer.getLastName());
                ps.setInt(3, customer.getAge());
                ps.setDouble(4, customer.getSalary());
                return ps;
            }, key);
        } catch (Exception ex) {
            throw new RuntimeException("Error getting Save Customer Details ".concat(ex.getMessage()));
        }
        return key.getKey();
    }

    @Override
    public List<Customer> getCustomers() {

        String query = "SELECT * FROM customer";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        try {
            return namedParameterJdbcTemplate.query(query, parameterSource, new CustomerMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean updateFirstName(String firstName) {
        try {

            String updateQuery = "UPDATE customer\n" +
                    "SET first_name = ?" +
                    "WHERE id = ?;";
            int i = jdbcTemplate.update(updateQuery, firstName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting update invoice fulfillment status" + firstName);
        }
    }

    @Override
    public boolean deleteCustomerById(int id) {
        try {
            String updateQuery = "DELETE FROM customer\n" +
                    "WHERE id = ?";
            int i = jdbcTemplate.update(updateQuery,id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting update invoice fulfillment status" + id);
        }
    }
}
