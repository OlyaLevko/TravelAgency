package com.itacademy.repository;

import com.itacademy.model.Hotel;
import com.itacademy.model.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long>{

     List<Order> getByUserId(Long id);

     List<Order> getActiveOrdersInHotel(Long hotelId);

     List<Order> getActiveOrdersInHotelByRoom(Long hotelId, Integer roomNumber);
}
