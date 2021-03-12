package com.itacademy.repository;


import com.itacademy.model.Order;
import com.itacademy.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    User getByEmail(String email);

    List<Order> getActiveOrdersForUser(Long id);
}
