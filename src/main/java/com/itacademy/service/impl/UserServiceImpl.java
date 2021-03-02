package com.itacademy.service.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
            return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
            userRepository.delete(id);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User getById(Long id) {
        User user =  userRepository.getById(id);
        if(user == null)
            throw new NotSuchElementException("User with id " + id +" doesn't exist.");
        else
            return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
