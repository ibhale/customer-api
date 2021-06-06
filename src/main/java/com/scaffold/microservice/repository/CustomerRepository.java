package com.scaffold.microservice.repository;

import com.scaffold.microservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository{
     List<Customer> findAll();
     void save(List<Customer> customerList);
     List<Customer> getCustomersPaginated(int size, int limit);

}
