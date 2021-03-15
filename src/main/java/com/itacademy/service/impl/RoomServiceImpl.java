package com.itacademy.service.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.exception.NullEntityReferenceException;
import com.itacademy.model.Order;
import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;
import com.itacademy.repository.OrderRepository;
import com.itacademy.repository.RoomRepository;
import com.itacademy.service.HotelService;
import com.itacademy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private OrderRepository orderRepository;
    private HotelService hotelService;

    public RoomServiceImpl(RoomRepository roomRepository, OrderRepository orderRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.orderRepository = orderRepository;
        this.hotelService = hotelService;
    }

    @Override
    public Room create(Room room) {
        if(room==null)
            throw new NullEntityReferenceException("room can not be null ");
        return roomRepository.save(room);
    }

    @Override
    public void delete(RoomCompositeId id) {
        roomRepository.delete(id);
    }

    @Override
    public Room update(Room room) {
        if(room==null)
            throw new NullEntityReferenceException("room can not be null ");
        return roomRepository.update(room);
    }

    @Override
    public Room getById(RoomCompositeId id) {
        Room room=roomRepository.getById(id);
        if(room==null)
            throw new NotSuchElementException("room not found");
        return room;
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

    @Override
    @Transactional
    public List<Room> getAvailableRoomsInHotelById(Long hotelId, LocalDate fromDate, LocalDate toDate) {
        List<Room> list = getAllRoomsInHotelById(hotelId);
         list.removeAll(orderRepository.getBookedRoomsInHotel(hotelId, fromDate, toDate));
         return list;
    }

    @Override
    @Transactional
    public boolean checkIfRoomIsAvailable(Long hotelId, Integer roomNumber, LocalDate fromDate, LocalDate toDate) {
        List<Room> list = orderRepository.getBookedRoomsInHotel(hotelId, fromDate, toDate);
        Room room = getById(new RoomCompositeId(hotelService.getById(hotelId), roomNumber));
        return !list.contains(room);
    }

    @Override
    public Room getRoom(Long hotelId, Integer number) {
        Room room=getById(new RoomCompositeId(hotelService.getById(hotelId), number));
        if(room==null)
            throw new NotSuchElementException("room with hotel_id: "+hotelId+" and number: "+number+" not found");
        return room;
    }

}
