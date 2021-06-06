package com.scaffold.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
@AllArgsConstructor
public class Customer {
    @Id
    private String customerId;

    @NotBlank(message = "Name is mandatory")
    private String customerName;

}
