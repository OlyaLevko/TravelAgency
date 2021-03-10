package com.itacademy.controller;

<<<<<<< HEAD
import com.itacademy.service.CountryService;
import lombok.extern.slf4j.Slf4j;
=======
import com.itacademy.model.Order;
import com.itacademy.model.User;
import com.itacademy.repository.OrderRepository;
import com.itacademy.repository.UserRepository;
import com.itacademy.security.SecurityUser;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
>>>>>>> master
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class HomeController {

    private CountryService countryService;

    public HomeController(CountryService countryService){
        this.countryService=countryService;
    }

    @GetMapping(value = "/")
    public String hello(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(a.getPrincipal());
        return "index";
    }

}
