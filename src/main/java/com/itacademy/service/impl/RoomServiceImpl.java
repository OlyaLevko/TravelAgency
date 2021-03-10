package com.itacademy.service.impl;

import com.itacademy.model.Order;
import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;
import com.itacademy.repository.OrderRepository;
import com.itacademy.repository.RoomRepository;
import com.itacademy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private OrderRepository orderRepository;

    public RoomServiceImpl(RoomRepository roomRepository, OrderRepository orderRepository) {
        this.roomRepository = roomRepository;
        this.orderRepository = orderRepository;
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

    @Override
    public List<Room> getAvailableRoomsInHotelById(Long hotelId, LocalDate fromDate, LocalDate toDate) {
        List<Order> orders = orderRepository.getActiveOrdersInHotel(hotelId);
        if (orders.isEmpty()) return getAllRoomsInHotelById(hotelId);
        Map<Room, Set<LocalDate>> booking = getMapOfDates(orders);
        List<Room> available = new ArrayList<>();
        for (Map.Entry<Room, Set<LocalDate>> room: booking.entrySet()) {
            for( LocalDate date = fromDate; date.isBefore(toDate.plusDays(1)); date = date.plusDays(1)){
                if(room.getValue().contains(date)) break;
                if(date == date.plusDays(1) && !room.getValue().contains(date))
                    available.add(room.getKey());
            }
        }
        return available;
    }

    @Override
    public boolean checkIfRoomIsAvailable(Long hotelId, Integer roomNumber, LocalDate fromDate, LocalDate toDate) {
        Set<LocalDate> dates = getBookedDatesForRoom(hotelId, roomNumber);
        if (dates.isEmpty()) return true;
        for( LocalDate date = fromDate; date.isBefore(toDate.plusDays(1)); date = date.plusDays(1)){
            if(dates.contains(date)) return false;
        }
        return true;
    }

    @Override
    public Set<LocalDate> getBookedDatesForRoom(Long hotelId, Integer roomNumber) {
        List<Order> orders = orderRepository.getActiveOrdersInHotelByRoom(hotelId, roomNumber);
        Set<LocalDate> dates = new HashSet<>();
        for (Order order: orders) {
            for( LocalDate date = order.getFromDate(); date.isBefore(order.getToDate().plusDays(1)); date = date.plusDays(1)){
                dates.add(date);
            }
        }
        return dates;
    }


    private Map<Room, Set<LocalDate>> getMapOfDates(List<Order> orders){
        Map<Room, Set<LocalDate>> booking = new HashMap<>();
        for (Order order: orders) {
            for( LocalDate date = order.getFromDate(); date.isBefore(order.getToDate().plusDays(1)); date = date.plusDays(1)){
                LocalDate finalDate = date;
                booking.computeIfPresent(order.getRoom(), (k, v)->{ v.add(finalDate);
                    return v;});
                booking.putIfAbsent(order.getRoom(), new HashSet<LocalDate>() { {add(finalDate);}});
            }
        }
        return booking;
    }
}
