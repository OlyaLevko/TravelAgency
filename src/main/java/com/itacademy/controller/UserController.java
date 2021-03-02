package com.itacademy.controller;

import com.itacademy.model.User;
import com.itacademy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping(value = "/users")
public class UserController {
    UserService userService;

 public UserController(UserService userService) {
  this.userService = userService;
 }

 @GetMapping("")//тестовий
 public String addUser(){
     User user = new User();
     user.setLastName("Brown");
     user.setFirstName("Dan");
     user.setEmail("den@email.com");
     user.setPassword("2222");
     userService.create(user);
     return "index";
 }
}
