package com.itacademy.service.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.model.Order;
import com.itacademy.repository.OrderRepository;
import com.itacademy.service.OrderService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderRepository  orderRepository;
    UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public Order create(Order order) {
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
            return null;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }


    @Override
    public List<Order> getByUserId(Long id) {
        return orderRepository.getByUserId(id);
    }

}
