package com.itacademy.repository;

import com.itacademy.model.Hotel;
import com.itacademy.model.Order;
import com.itacademy.model.Room;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

     List<Order> getByUserId(Long id);


     List<Order> getActiveOrdersInHotelByRoom(Long hotelId, Integer roomNumber);

     List<Room> getBookedRoomsInHotel(Long hotelId, LocalDate fromDate, LocalDate toDate);
}
