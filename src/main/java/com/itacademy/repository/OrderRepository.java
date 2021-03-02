package com.itacademy.repository;

import com.itacademy.model.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long>{

     List<Order> getByUserId(Long id);
}
