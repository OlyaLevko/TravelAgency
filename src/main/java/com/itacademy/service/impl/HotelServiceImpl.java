package com.itacademy.service.impl;

import com.itacademy.model.Hotel;
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

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
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
}
