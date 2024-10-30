package com.brewery.fuzzy_couscous.services;

import com.brewery.fuzzy_couscous.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerSerivceImpl implements CustomerSerivce {
    @Override
    public CustomerDto getCustomerById(UUID customerid) {
        return CustomerDto.builder().customerId(UUID.randomUUID()).firstName("Pepe").lastName("Honguito").build();
    }
}
