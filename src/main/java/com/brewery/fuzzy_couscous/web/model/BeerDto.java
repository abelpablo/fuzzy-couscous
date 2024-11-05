package com.brewery.fuzzy_couscous.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BEERS")
public class BeerDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "beer_name")
    private String beerName;

    @Column(name = "beer_Style")
    private String beerStyle;

    @Column(name = "beer_upc")
    private Long upc;

}
