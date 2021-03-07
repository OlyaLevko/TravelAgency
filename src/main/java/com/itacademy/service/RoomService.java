package com.itacademy.service;

import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomService extends CrudService<Room, RoomCompositeId>{
    List<Room> getAllRoomsInHotel(String hotelName);

    List<Room> getAllRoomsInHotelById(Long hotelId);

    List<Room> getAvailableRoomsInHotelById(Long hotelId, LocalDate fromDate, LocalDate toDate);

    boolean checkIfRoomIsAvailable(Long hotelId, Integer roomNumber, LocalDate fromDate, LocalDate toDate);

    Set<LocalDate> getBookedDatesForRoom(Long hotelId, Integer roomNumber);

}
