package com.itacademy.repository;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long>{

    Country getByCountryName(String name);

    List<Hotel> getAllHotelsInCountry(String countryName);
}
