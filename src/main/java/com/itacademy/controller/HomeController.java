package com.itacademy.controller;

import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    private UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String hello(){
        User user = new User();
        user.setFirstname("Vasyl");
        user.setLastName("Prokopiv");
        userRepository.save(user);
        return "index";
    }
}
