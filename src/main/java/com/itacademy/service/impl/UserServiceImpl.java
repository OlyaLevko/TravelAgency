package com.itacademy.service.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
            return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        if(!userRepository.getActiveOrdersForUser(id).isEmpty())
            throw new UnsupportedOperationException("User has active orders. Please cancel or mark as done all user's active orders.");

        userRepository.delete(id);
    }

    @Override
    public User update(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
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

    @Override
    public User getByEmail(String email) {
            return userRepository.getByEmail(email);
    }

    @Override
    public User createOrGetByEmail(User user) {
        try {
           user = userRepository.save(user);
        }catch (UnsupportedOperationException e) {
            user = userRepository.getByEmail(user.getEmail());
        }
        return user;
    }


}
