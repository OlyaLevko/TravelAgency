package com.itacademy.controller;

import com.itacademy.dto.HotelDto;
import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import com.itacademy.service.CountryService;
import com.itacademy.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private HotelService hotelService;
    private CountryService countryService;

    @Autowired
    public HotelController(HotelService hotelService, CountryService countryService) {
        this.hotelService = hotelService;
        this.countryService=countryService;
    }

    @GetMapping("/add")
    public String add(){
        return "create-hotel";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute HotelDto hotelDto ){
        Hotel hotel=HotelDto.convertToHotel(hotelDto);

        hotelService.create(hotel);
        return "index";
    }

    @GetMapping("/all")
    public String allHotels(ModelMap model){
        List<Hotel> hotels=null;
        hotels=hotelService.getAll();
        model.addAttribute("hotels", hotels);
        return "hotels-list";
    }

    @GetMapping("/demo")
    public String demo(){

        Hotel hotel=new Hotel();
        hotel.setName("Trump");
        hotel.setRooms(new ArrayList<>());
        Country country=countryService.getByCountryName("China");
        hotel.setCountry(country);
        hotel.setStars(4);

        hotelService.create(hotel);

        return "index";
    }

}