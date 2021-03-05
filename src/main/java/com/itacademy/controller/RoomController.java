package com.itacademy.controller;

import com.itacademy.dto.RoomDto;
import com.itacademy.model.Hotel;
import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;
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
    private RoomDto roomDto;

    @Autowired
    public RoomController(RoomService roomService,HotelService hotelService,RoomDto roomDto) {
        this.roomService = roomService;
        this.hotelService=hotelService;
        this.roomDto=roomDto;
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

    @PostMapping("/create")
    public String createRoom( @ModelAttribute RoomDto roomDto, Model model){

        Hotel hotel=hotelService.getById(roomDto.getHotel_id());

        Room room =this.roomDto.convertToRoom(roomDto);
//        room.setId(new RoomCompositeId(hotel,roomDto.getNumber()));
        roomService.create(room);
        model.addAttribute("types", Type.values());
        model.addAttribute("hotel",hotel);
        return "create-room";
    }

    @GetMapping("/update")
    public String updateRoomForm(@ModelAttribute RoomCompositeId id, Model model){
        model.addAttribute("room",roomService.getById(id));
        return "room-update";
    }

    @PostMapping("/update")
    public String updateRoom(@ModelAttribute Room room, Model model){
        roomService.update(room);
        return "redirect:/"+room.getId().getHotel().getId()+"/all"; // todo fix redirect link  ==="+room.getHotel().getId()+"
    }

}
