package com.itacademy.service.impl;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.repository.CountryRepository;
import com.itacademy.repository.HotelRepository;
import com.itacademy.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private CountryRepository countryRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository,CountryRepository countryRepository) {
        this.hotelRepository = hotelRepository;
        this.countryRepository=countryRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
        Country countryFromDb=countryRepository.getByCountryName(hotel.getCountry().getName());
        hotel.setCountry(countryFromDb);
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Long id) {
        hotelRepository.delete(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return hotelRepository.update(hotel);
    }

    @Override
    public Hotel getById(Long id) {
        return hotelRepository.getById(id);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.getAll();
    }


    @Override
    public List<Hotel> getByCountry(Long id) {
        return hotelRepository.getByCountry(id);
    }
}
