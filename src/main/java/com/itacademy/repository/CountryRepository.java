package com.itacademy.repository;

import com.itacademy.model.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{

    Country getByCountryName(String name);
}
