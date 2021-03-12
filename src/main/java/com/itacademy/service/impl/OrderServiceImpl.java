package com.itacademy.service.impl;

import com.itacademy.exception.DateFormatException;
import com.itacademy.exception.NotSuchElementException;
import com.itacademy.model.Order;
import com.itacademy.model.OrderStatus;
import com.itacademy.repository.OrderRepository;
import com.itacademy.service.OrderService;
import com.itacademy.service.RoomService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository  orderRepository;
    UserService userService;
    RoomService roomService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, RoomService roomService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    public Order create(Order order) {
        if(order.getFromDate().isAfter(order.getToDate()))
            throw new DateFormatException("Start date must be until end date.");
        if(!roomService.checkIfRoomIsAvailable(order.getRoom().getId().getHotel().getId(),order.getRoom().getId().getNumber(),order.getFromDate(), order.getToDate()))
            throw new UnsupportedOperationException("Room is not available for period " +
                    order.getFromDate() + " + " + order.getToDate());
        order.setStatus(OrderStatus.ACTIVE);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
         orderRepository.delete(id);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.update(order);
    }

    @Override
    public Order getById(Long id) {
        Order order = orderRepository.getById(id);
        if (order == null)
            throw new NotSuchElementException("There are not order with id " + id);
        else
            return orderRepository.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }


    @Override
    public List<Order> getByUserId(Long id) {
        return orderRepository.getByUserId(id);
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        Order order = getById(id);
        order.setStatus(OrderStatus.CANCELED);
        update(order);
    }

    @Override
    public void done(Long id) {
        Order order = getById(id);
        order.setStatus(OrderStatus.DONE);
        update(order);
    }

}
