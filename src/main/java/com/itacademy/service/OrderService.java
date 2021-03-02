package com.itacademy.service;

import com.itacademy.model.Order;

import java.util.List;

public interface OrderService extends  CrudService<Order, Long>{

    List<Order> getByUserId(Long id);
}
