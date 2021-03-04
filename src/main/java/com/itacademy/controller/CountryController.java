package com.itacademy.controller;

import com.itacademy.model.Country;
import com.itacademy.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country/{id}")
    public String countryInfo(@PathVariable Long id, ModelAndView mav){

        Country country=countryService.getById(id);
        mav.addObject("country", country);
        return "country-info";
    }
}
