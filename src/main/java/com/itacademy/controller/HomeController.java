package com.itacademy.controller;

import com.itacademy.service.CountryService;
import lombok.extern.slf4j.Slf4j;
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
    public String hello(Model model){

        return "index";
    }

}
