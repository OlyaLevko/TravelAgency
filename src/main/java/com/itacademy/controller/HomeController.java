package com.itacademy.controller;

import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/")
    public String hello(){
        return "index";
    }

    @GetMapping(value = "/test")
    public String test(){
        User user=new User();
        user.setFirstName("Den");
        user.setEmail("wrwer@mail.com");
        user.setLastName("Levron");
        user.setPassword("sdfsdfsdfsd12312312$!!ASADAD!#");

        userRepository.save(user);
        return "index";
    }
}
