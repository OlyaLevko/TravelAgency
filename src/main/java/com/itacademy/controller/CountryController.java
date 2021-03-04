package com.itacademy.controller;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.service.CountryService;
import com.itacademy.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;


    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{id}")
    public String countryInfo(@PathVariable Long id, Model model){
        Country country=countryService.getById(id);
        log.info("============ "+country.toString());

        List<Hotel> hotels=countryService.getAllHotelsInCountry(country.getName());

        model.addAttribute("country", country);
        model.addAttribute("hotels", hotels);
        return "country-info";
    }
    @GetMapping("/all")
    public String getCountryList(Model model){
        model.addAttribute("countries", countryService.getAll());
        return "country-list";
    }


}
