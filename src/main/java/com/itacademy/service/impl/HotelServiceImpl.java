package com.itacademy.service.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.exception.NullEntityReferenceException;
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
        if(hotel==null)
            throw new NullEntityReferenceException("hotel can not be null ! ");
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Long id) {
        hotelRepository.delete(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        if(hotel==null)
            throw new NullEntityReferenceException("hotel can not be null ! ");
        return hotelRepository.update(hotel);
    }

    @Override
    public Hotel getById(Long id) {
        Hotel hotel=hotelRepository.getById(id);
        if(hotel==null)
            throw new NotSuchElementException("hotel with id: "+id+" doesn't found");
        return hotel;
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> hotels=hotelRepository.getAll();
        if(hotels==null || hotels.isEmpty())
            throw new NotSuchElementException("hotels list is null or empty");
        return hotels;
    }


    @Override
    public List<Hotel> getByCountry(Long id) {
        List<Hotel> hotels=hotelRepository.getByCountry(id);
        if(hotels==null || hotels.isEmpty())
            throw new NotSuchElementException("in country with id: "+ id+" hotels list is null or empty");
        return hotels;
    }
}
