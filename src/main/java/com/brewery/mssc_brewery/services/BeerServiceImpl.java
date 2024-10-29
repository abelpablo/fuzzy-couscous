package com.brewery.mssc_brewery.services;

import com.brewery.mssc_brewery.web.model.BeerDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Component
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Stella")
                .beerStyle("IPA")
                .build();
    }
}
