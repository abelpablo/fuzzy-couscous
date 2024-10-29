package com.brewery.mssc_brewery.services;

import com.brewery.mssc_brewery.web.model.BeerDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}