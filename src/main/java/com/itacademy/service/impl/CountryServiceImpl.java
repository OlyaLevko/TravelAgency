package com.itacademy.service.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.exception.NullEntityReferenceException;
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
        if(country==null)
            throw new NullEntityReferenceException("country can not be null ! ");

        Country countryFromDb=countryRepository.save(country);
        if(countryFromDb==null){
            throw new NotSuchElementException("save operation failed");
        }
        return countryFromDb;
    }

    @Override
    public void delete(Long id) {
        countryRepository.delete(id);
    }

    @Override
    public Country update(Country country) {
        if(country==null)
            throw new NullEntityReferenceException("country can not be null ! ");

        Country countryFromDb=countryRepository.update(country);
        if(countryFromDb==null){
            throw new NotSuchElementException("update operation failed");
        }
        return countryFromDb;
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries= countryRepository.getAll();
        if(countries==null){
            throw new NotSuchElementException("there are no countries ");
        }
        return countries;
    }

    @Override
    public List<Hotel> getAllHotelsInCountry(String countryName) {
        if(countryName==null)
            throw new NullEntityReferenceException("countryName is null ! ");

        List<Hotel> hotels=countryRepository.getAllHotelsInCountry(countryName);
        if(hotels==null || hotels.isEmpty())
            throw new NotSuchElementException("there are no hoteles");
        return hotels;
    }
}
