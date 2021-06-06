package com.scaffold.microservice.repository.impl;

import com.scaffold.microservice.model.Customer;
import com.scaffold.microservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Slf4j
public class CustomerRepositoryImpl implements CustomerRepository {
    static long count = 0L;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Customer> findAll() {

        return mongoTemplate.findAll(Customer.class);
    }

    @Override
    public void save(List<Customer> customerList) {

        mongoTemplate.insertAll(customerList);

    }

    @Override
    public List<Customer> getCustomersPaginated(int pageSize, int pageLimit) {
        Query query = new Query();
      query.skip(pageLimit *pageLimit);
      query.limit(pageLimit);
      return mongoTemplate.find(query, Customer.class);
    }
}
