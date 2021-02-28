package com.itacademy.repository;


import com.itacademy.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User save(User user);
}
