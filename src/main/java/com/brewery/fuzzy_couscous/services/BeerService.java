package com.brewery.fuzzy_couscous.services;

import com.brewery.fuzzy_couscous.web.model.BeerDto;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    List<BeerDto> findAllBeers();
}