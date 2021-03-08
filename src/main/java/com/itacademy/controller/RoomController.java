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
    private RoomDto roomDtoBean;

    @Autowired
    public RoomController(RoomService roomService,HotelService hotelService,RoomDto roomDtoBean) {
        this.roomService = roomService;
        this.hotelService=hotelService;
        this.roomDtoBean = roomDtoBean;
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

        Room room =this.roomDtoBean.convertToRoom(roomDto);
//        room.setId(new RoomCompositeId(hotel,roomDto.getNumber()));
        roomService.create(room);
        model.addAttribute("types", Type.values());
        model.addAttribute("hotel",hotel);
        return "create-room";
    }

    @GetMapping("/update")
    public String updateRoomForm(@RequestParam Long hotel_id,
                                 @RequestParam Integer number, Model model){
        RoomCompositeId id=new RoomCompositeId(hotelService.getById(hotel_id),number);
        model.addAttribute("room",roomService.getById(id));
        model.addAttribute("types",Type.values());
        return "room-update";
    }

    @PostMapping("/update")
    public String updateRoom(@ModelAttribute RoomDto roomDto){
        Room room= roomDtoBean.convertToRoom(roomDto);
        roomService.update(room);
        return "redirect:/room/"+room.getId().getHotel().getId()+"/all"; // todo fix redirect link  ==="+room.getHotel().getId()+"
    }

    @PostMapping("/delete")
    public String roomDelete(@RequestParam Long hotel_id,
                             @RequestParam Integer number){
        RoomCompositeId roomId=new RoomCompositeId(hotelService.getById(hotel_id),number);
        roomService.delete(roomId);
        return "redirect:/"+hotel_id+"/all";
    }
}
