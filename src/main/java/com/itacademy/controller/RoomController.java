package com.itacademy.controller;

import com.itacademy.dto.RoomDto;
import com.itacademy.model.*;
import com.itacademy.service.HotelService;
import com.itacademy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        List<Room> rooms=roomService.getAllRoomsInHotelById(hotel_id);
        List<List<Room>> roomGroups=new ArrayList<>();
        System.out.println(rooms.size());
        if(rooms!=null&&!rooms.isEmpty()){
            if(rooms.size()<=3){
                roomGroups.add(rooms);
            }else {
                for (int i = 0; i < rooms.size() - 3; i += 3) {
                    roomGroups.add(rooms.subList(i, i + 3));
                }
                roomGroups.add(rooms.subList(rooms.size() - 3, rooms.size() ));
            }
        }
        System.out.println(roomGroups.size());
        model.addAttribute("hotel",hotelService.getById(hotel_id) );
        model.addAttribute("roomGroups", roomGroups);
        return "rooms-list";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/create/{hotel_id}")
    public String createRoomForm(@PathVariable Long hotel_id, Model model){
        model.addAttribute("types", Type.values());
        model.addAttribute("roomDto", new RoomDto());
        model.addAttribute("hotel",hotelService.getById(hotel_id));
        return "create-room";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/create")
    public String createRoom(@Validated @ModelAttribute RoomDto roomDto, BindingResult result, Model model){

        if(result.hasErrors()){
//            model.addAttribute("types", Type.values());
            return "create-room";
        }
        Hotel hotel=hotelService.getById(roomDto.getHotel_id());

        Room room =this.roomDtoBean.convertToRoom(roomDto);
        roomService.create(room);
        model.addAttribute("types", Type.values());
        model.addAttribute("hotel",hotel);
        model.addAttribute("roomDto", new RoomDto());
        return "create-room";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{number}/update/{hotel_id}")
    public String updateRoomForm(@PathVariable Integer number,
                                 @PathVariable Long hotel_id, Model model){
        RoomCompositeId id=new RoomCompositeId(hotelService.getById(hotel_id),number);
        Room room=roomService.getById(id);
        model.addAttribute("room",roomDtoBean.convertToDto(room));
        model.addAttribute("hotel",room.getId().getHotel());
        model.addAttribute("types",Type.values());
        return "room-update";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/update")
    public String updateRoom(@Valid @ModelAttribute RoomDto roomDto, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/room/"+roomDto.getNumber()+"/update/"+roomDto.getHotel_id();
        }
        Room room= roomDtoBean.convertToRoom(roomDto);
        roomService.update(room);
        return "redirect:/room/"+room.getId().getHotel().getId()+"/all";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/delete")
    public String roomDelete(@RequestParam Long hotel_id,
                             @RequestParam Integer number){
        RoomCompositeId roomId=new RoomCompositeId(hotelService.getById(hotel_id),number);
        roomService.delete(roomId);
        return "redirect:/room/"+hotel_id+"/all";
    }
}
