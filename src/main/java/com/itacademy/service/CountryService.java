package com.itacademy.service;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;

import java.util.List;

public interface CountryService extends CrudService<Country, Long>{

    Country getByCountryName(String name);

    List<Hotel> getAllHotelsInCountry(String countryName);
}
