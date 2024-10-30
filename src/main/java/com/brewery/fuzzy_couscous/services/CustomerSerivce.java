package com.brewery.fuzzy_couscous.services;

import com.brewery.fuzzy_couscous.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerSerivce {
    CustomerDto getCustomerById(UUID customerid);
}
