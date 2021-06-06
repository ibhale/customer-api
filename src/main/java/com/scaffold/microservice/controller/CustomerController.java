package com.scaffold.microservice.controller;

import com.scaffold.microservice.exception.custom.CustomerNotFoundException;
import com.scaffold.microservice.model.Customer;
import com.scaffold.microservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class CustomerController {
    @Autowired
    private  CustomerRepository customerRepository;

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.info("test");
        return new ResponseEntity<>("Hello " + name, HttpStatus.OK);
    }

    @PostMapping("/customers/{customerId}")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody List<Customer> customer, @PathVariable("customerId") String customerId,
                                         @RequestParam("pagesize") int pageSize, @RequestParam("pagelimit") int pageLimit) {
        log.info("test");
        if(customerId.equals("1234"))
            throw new CustomerNotFoundException("Blacklisted Customer");
        customerRepository.save(customer);
        return new ResponseEntity<>("Customer added-" + customerId, HttpStatus.CREATED);
    }
    @GetMapping("/customers")
    public List<Customer> getCustomer() {
        log.info("test");
        return (List<Customer>)customerRepository.findAll();
    }

}