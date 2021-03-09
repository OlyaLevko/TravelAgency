package com.itacademy.controller;

import com.itacademy.dto.HotelDto;
import com.itacademy.model.Hotel;
import com.itacademy.model.Type;
import com.itacademy.service.CountryService;
import com.itacademy.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String add(@Validated @ModelAttribute HotelDto hotelDto, BindingResult result, Model model ){
        Hotel hotel=HotelDto.convertToHotel(hotelDto);

        hotel=hotelService.create(hotel);
        model.addAttribute("hotel",hotel);
        model.addAttribute("types",Type.values());
        return "create-room";
    }

    @GetMapping("/all")
    public String allHotels(Model model){
        List<Hotel> hotels=hotelService.getAll();
        model.addAttribute("hotels", hotels);
        return "hotels-list";
    }
    @GetMapping("/{hotel_id}")
    public String hotelsRoomsList(@PathVariable Long hotel_id){
        return "redirect:/room/"+hotel_id+"/all";
    }

    @GetMapping("/{hotel_id}/update")
    public String updateHotelPage(@PathVariable Long hotel_id, Model model ){
        Hotel hotelFromDb=hotelService.getById(hotel_id);
        model.addAttribute("hotel",hotelFromDb);
        return "hotel-update";
    }

    @PostMapping("/update")
    public String updateHotel(@Validated @ModelAttribute Hotel hotel, BindingResult result,Model model){
        if(result.hasErrors()){
            return "hotel/update";
        }
        hotelService.update(hotel);
        return "redirect:hotels/all";
    }

    @PostMapping("/{hotel_id}/delete")
    public String deleteHotel(@PathVariable Long hotel_id, Model model){
        hotelService.delete(hotel_id);
        return "redirect:/hotels/all";
    }

}