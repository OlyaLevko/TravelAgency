package com.itacademy.controller;

import com.itacademy.model.Hotel;
import com.itacademy.model.Room;
import com.itacademy.model.Type;
import com.itacademy.service.HotelService;
import com.itacademy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;
    private HotelService hotelService;

    @Autowired
    public RoomController(RoomService roomService,HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService=hotelService;
    }

    @GetMapping("/{hotel_id}/all")
    public String getAllRooms(@PathVariable Long hotel_id, Model model){
        model.addAttribute("hotel",hotelService.getById(hotel_id) );
        model.addAttribute("rooms",roomService.getAllRoomsInHotelById(hotel_id));
        return "rooms-list";
    }

    @GetMapping("/create/{hotel_id}")
    public String createRoomForm(@PathVariable Long hotel_id, Model model){
        model.addAttribute("types", Type.values());
        model.addAttribute("hotel",hotelService.getById(hotel_id));
        return "create-room";
    }

    @PostMapping("/create/{hotel_id}")
    public String createRoom(@PathVariable Long hotel_id,
                             @ModelAttribute Room room, Model model){
        Hotel hotel=hotelService.getById(hotel_id);
        room.setHotel(hotel); //todo  how save hotel_id field to db
        roomService.create(room);
        model.addAttribute("types", Type.values());
        model.addAttribute("hotel",hotel);
        return "create-room";
    }

    @GetMapping("/update")
    public String updateRoomForm(){
        return null;
    }

    @PostMapping("/update")
    public String updateRoom(){
        return null;
    }

}
