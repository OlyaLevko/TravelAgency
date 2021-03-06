package com.itacademy.controller;

import com.itacademy.model.Order;
import com.itacademy.model.User;
import com.itacademy.repository.OrderRepository;
import com.itacademy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String hello(){

        return "index";
    }

}
