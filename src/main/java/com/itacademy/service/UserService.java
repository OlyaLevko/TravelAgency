package com.itacademy.service;

import com.itacademy.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService extends CrudService<User,Long>{
    User getByEmail(String email);
    User createOrGetByEmail(User user);
}
