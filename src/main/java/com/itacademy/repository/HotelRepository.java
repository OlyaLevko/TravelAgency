package com.itacademy.repository;

import com.itacademy.model.Hotel;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long>{
    List<Hotel> getByCountry(Long id);
}
