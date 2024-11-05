package com.brewery.fuzzy_couscous.services;

import com.brewery.fuzzy_couscous.web.Repository.BeerRepository;
import com.brewery.fuzzy_couscous.web.model.BeerDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerRepository.getBeerDtoById(beerId);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerRepository.save(BeerDto.builder().id(UUID.randomUUID())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .upc(beerDto.getUpc())
                .build());
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
    }

    @Override
    public List<BeerDto> findAllBeers() {
        return beerRepository.findAll();
    }
}
