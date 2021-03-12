package com.itacademy.service.impl;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.repository.CountryRepository;
import com.itacademy.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country getById(Long id) {
        return countryRepository.getById(id);
    }


    @Override
    public Country getByCountryName(String name) {
        Country countryFromDb=countryRepository.getByCountryName(name);
        if(countryFromDb==null){
            Country country=new Country();
            country.setName(name);
            countryFromDb=create(country);
        }
        return countryFromDb;
    }

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.delete(id);
    }

    @Override
    public Country update(Country country) {
        return countryRepository.update(country);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.getAll();
    }

    @Override
    public List<Hotel> getAllHotelsInCountry(String countryName) {

        return countryRepository.getAllHotelsInCountry(countryName);
    }
}
