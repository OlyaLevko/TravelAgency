package com.itacademy.dto;

import com.itacademy.model.Hotel;
import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;
import com.itacademy.model.Type;
import com.itacademy.repository.impl.HotelRepositoryImpl;
import com.itacademy.service.HotelService;
import com.itacademy.service.RoomService;
import com.itacademy.service.impl.HotelServiceImpl;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EqualsAndHashCode
@ToString
@Component
public class RoomDto {

    private Long hotel_id;
    private Integer number;
    private Type type;
    private Long price;

    private HotelService hotelService;

    @Autowired
    public RoomDto(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public Room convertToRoom(RoomDto roomDto){
        Room room =new Room();
        Hotel hotel=hotelService.getById(roomDto.getHotel_id());
        room.setId(new RoomCompositeId(hotel, roomDto.number));
        room.setType(roomDto.type);
        room.setPrice(roomDto.price);
        return room;
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
