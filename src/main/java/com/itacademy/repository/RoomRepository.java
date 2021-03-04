package com.itacademy.repository;

import com.itacademy.model.Room;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long>{
    List<Room> getAllRoomsInHotel(String hotelName);

    List<Room> getAllRoomsInHotelById(Long id);
}
