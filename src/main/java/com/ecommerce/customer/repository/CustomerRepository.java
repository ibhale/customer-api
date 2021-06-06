package com.ecommerce.customer.repository;

import com.ecommerce.customer.model.Customer;

import java.util.List;

public interface CustomerRepository{
     List<Customer> findAll();
     void save(List<Customer> customerList);
     List<Customer> getCustomersPaginated(int size, int limit);

}
