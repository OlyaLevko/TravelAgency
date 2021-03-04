package com.itacademy.service;

import com.itacademy.model.Room;

import java.util.List;

public interface RoomService extends CrudService<Room, Long>{
    List<Room> getAllRoomsInHotel(String hotelName);

    List<Room> getAllRoomsInHotelById(Long hotelId);
}
