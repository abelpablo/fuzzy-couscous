package com.brewery.fuzzy_couscous.web.Repository;

import com.brewery.fuzzy_couscous.web.model.BeerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<BeerDto, UUID> {
    BeerDto getBeerDtoById(UUID beerId);
    // Additional custom queries can go here
}

