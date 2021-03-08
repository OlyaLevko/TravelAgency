package com.itacademy.controller;

import com.itacademy.dto.HotelDto;
import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.model.Type;
import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import com.itacademy.service.CountryService;
import com.itacademy.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String add(Model model){
        return "create-hotel";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute HotelDto hotelDto,Model model ){
        Hotel hotel=HotelDto.convertToHotel(hotelDto);

        hotel=hotelService.create(hotel);
        model.addAttribute("hotel",hotel);
        model.addAttribute("types",Type.values());
        return "create-room";
    }

    @GetMapping("/all")
    public String allHotels(Model model){
        List<Hotel> hotels=null;
        hotels=hotelService.getAll();
        model.addAttribute("hotels", hotels);
        return "hotels-list";
    }

    @GetMapping("/{hotel_id}/update")
    public String updateHotelPage(@PathVariable Long hotel_id, Model model ){
        Hotel hotelFromDb=hotelService.getById(hotel_id);
        model.addAttribute("hotel",hotelFromDb);
        return "hotel-update";
    }

    @PostMapping("/update")
    public String updateHotel(@ModelAttribute Hotel hotel, Model model ){
        hotelService.update(hotel);
        return "redirect:hotels/all";
    }

    @PostMapping("/{hotel_id}/delete")
    public String deleteHotel(@PathVariable Long hotel_id, Model model){
        hotelService.delete(hotel_id);
        return "redirect:/country/"+hotel_id+"/hotels";
    }

}