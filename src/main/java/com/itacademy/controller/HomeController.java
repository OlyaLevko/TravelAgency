package com.itacademy.controller;

import com.itacademy.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
