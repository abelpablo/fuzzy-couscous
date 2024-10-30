package com.brewery.fuzzy_couscous.web.controller;

import com.brewery.fuzzy_couscous.services.CustomerSerivce;
import com.brewery.fuzzy_couscous.web.model.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerSerivce customerSerivce;

    public CustomerController(CustomerSerivce customerSerivce) {
        this.customerSerivce = customerSerivce;
    }

    @GetMapping("/{customerid}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerid") UUID customerid) {

        return new ResponseEntity<>(customerSerivce.getCustomerById(customerid), HttpStatus.OK);

    }
}
