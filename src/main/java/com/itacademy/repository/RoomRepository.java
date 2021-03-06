package com.itacademy.repository;

import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, RoomCompositeId>{
    List<Room> getAllRoomsInHotel(String hotelName);

    List<Room> getAllRoomsInHotelById(Long id);
}
