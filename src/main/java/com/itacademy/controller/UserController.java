package com.itacademy.controller;

import com.itacademy.model.Role;
import com.itacademy.model.User;
import com.itacademy.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/users")
public class UserController {
    UserService userService;

 public UserController(UserService userService) {
  this.userService = userService;
 }

 @GetMapping("/add")
 public String addUser(Model model){
     User user = new User();
    model.addAttribute("user", user);
     return "create-user";
 }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
           return "create-user";
        }
        userService.create(user);
        return "redirect:/";
    }

    @GetMapping("/all")
    @PreAuthorize(value = "hasAuthority('MANAGER')")
    public String allUsers(Model model){
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }

    @GetMapping("/{id}/update")
    @PreAuthorize(value = "hasAuthority('MANAGER') or #id == @userServiceImpl.getByEmail(authentication.name).getId()")
    public String updateUser(@PathVariable Long id, Model model){
        model.addAttribute("roles", Role.values());
        model.addAttribute("user", userService.getById(id));
        return "update-user";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@Validated @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()){
         model.addAttribute("roles", Role.values());
            return "update-user";
        }
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize(value = "hasAuthority('MANAGER')")
    public String deleteUser(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/users/all";
    }

}