package com.itacademy.service.impl;

import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;
import com.itacademy.repository.RoomRepository;
import com.itacademy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(RoomCompositeId id) {
        roomRepository.delete(id);
    }

    @Override
    public Room update(Room room) {
        return roomRepository.update(room);
    }

    @Override
    public Room getById(RoomCompositeId id) {
        return roomRepository.getById(id);
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.getAll();
    }

    @Override
    public List<Room> getAllRoomsInHotel(String hotelName) {
        return roomRepository.getAllRoomsInHotel(hotelName);
    }

    @Override
    public List<Room> getAllRoomsInHotelById(Long hotelId) {
        return roomRepository.getAllRoomsInHotelById(hotelId);
    }
}
