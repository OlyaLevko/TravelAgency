package com.itacademy.controller;

import com.itacademy.dto.HotelDto;
import com.itacademy.model.Hotel;
import com.itacademy.model.Type;
import com.itacademy.service.CountryService;
import com.itacademy.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hotels")
@Slf4j
public class HotelController {

    private HotelService hotelService;
    private CountryService countryService;
    private HotelDto hotelDtoBean;

    @Autowired
    public HotelController(HotelService hotelService, CountryService countryService,HotelDto hotelDto) {
        this.hotelService = hotelService;
        this.countryService=countryService;
        this.hotelDtoBean=hotelDto;
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/add")
    public String add(Model model){
        return "create-hotel";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute HotelDto hotelDto, BindingResult result, Model model ){
        if(result.hasErrors()){
            return "create-hotel";
        }
        Hotel hotel=hotelDtoBean.convertToHotel(hotelDto);

        hotel=hotelService.create(hotel);
        model.addAttribute("hotel",hotel);
        model.addAttribute("types",Type.values());
        return "create-room";
    }

//    @GetMapping("/all")
//    public String allHotels(Model model){
//        List<Hotel> hotels=hotelService.getAll();
//        model.addAttribute("hotels", hotels);
//        return "hotels-list";
//    }
    @GetMapping("/{hotel_id}")
    public String hotelsRoomsList(@PathVariable Long hotel_id){
        return "redirect:/room/"+hotel_id+"/all";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{hotel_id}/update")
    public String updateHotelPage(@PathVariable Long hotel_id, Model model ){
        Hotel hotelFromDb=hotelService.getById(hotel_id);
        model.addAttribute("hotel",hotelDtoBean.convertToHotelDto(hotelFromDb));
        return "hotel-update";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/update")
    public String updateHotel(@Validated @ModelAttribute HotelDto hotelDto, BindingResult result,Model model){
        if(result.hasErrors()){
            log.info("==== update==="+result.toString());
            return "hotel-update";
        }
        Hotel hotel=hotelDtoBean.convertToHotel(hotelDto);
        hotelService.update(hotel);
        return "redirect:hotels/all";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/{hotel_id}/delete")
    public String deleteHotel(@PathVariable Long hotel_id, Model model){
        hotelService.delete(hotel_id);
        return "redirect:/hotels/all";
    }

}