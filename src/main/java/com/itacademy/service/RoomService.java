package com.itacademy.service;

import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;

import java.time.LocalDate;
import java.util.List;

public interface RoomService extends CrudService<Room, RoomCompositeId>{
    List<Room> getAllRoomsInHotel(String hotelName);

    List<Room> getAllRoomsInHotelById(Long hotelId);

    List<Room> getAvailableRoomsInHotelById(Long hotelId, LocalDate fromDate, LocalDate toDate);



}
