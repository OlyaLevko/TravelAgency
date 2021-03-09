package com.itacademy.dto;

import com.itacademy.model.Hotel;
import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;
import com.itacademy.model.Type;
import com.itacademy.service.HotelService;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@ToString
@Component
public class RoomDto {

    @NotNull
    private Long hotel_id;
    @NotNull
    private Integer number;
    private Type type;
    @NotNull
    @Range()
    private Long price;

    private HotelService hotelService;

    @Autowired
    public RoomDto(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public RoomDto() {

    }


    public Room convertToRoom(RoomDto roomDto){
        Room room =new Room();
        Hotel hotel=hotelService.getById(roomDto.getHotel_id());
        room.setId(new RoomCompositeId(hotel, roomDto.number));
        room.setType(roomDto.type);
        room.setPrice(roomDto.price);
        return room;
    }

    public RoomDto convertToDto(Room room){
        RoomDto roomDto=new RoomDto();
        roomDto.setHotel_id(room.getId().getHotel().getId());
        roomDto.setNumber(room.getId().getNumber());
        roomDto.setPrice(room.getPrice());
        roomDto.setType(room.getType());
        return roomDto;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
