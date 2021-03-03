package com.itacademy.controller;

import com.itacademy.model.User;
import com.itacademy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     user.setEmail("dan@email.com");
     user.setPassword("2222");
     userService.update(user);
     return "index";
 }

    @GetMapping("/{id}")//тестовий
    public String deleteUser(@PathVariable Long id){
        userService.delete(id);
        return "index";
    }
}
