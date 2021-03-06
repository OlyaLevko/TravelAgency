package com.itacademy.service;

import com.itacademy.model.Hotel;

import java.util.List;

public interface HotelService extends CrudService<Hotel, Long>{

    List<Hotel> getByCountry(Long id);


}
