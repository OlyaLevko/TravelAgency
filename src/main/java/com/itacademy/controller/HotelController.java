package com.itacademy.controller;

import com.itacademy.dto.HotelDto;
import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import com.itacademy.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
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
    public String allHotels(ModelAndView mav){
        List<Hotel> hotels=new ArrayList<>();
        Hotel h=new Hotel();
        h.setName("First");
        h.setCountry(null);
        hotels.add(h);
        mav.addObject("hotels", hotels );
        return "hotels-list";
    }

    @GetMapping("/demo")
    public String demo(){

        Hotel hotel=new Hotel();
        hotel.setName("Trump");
        hotel.setRooms(new ArrayList<>());
        hotel.setCountry(new Country());

        hotelService.create(hotel);

        return "index";
    }

}