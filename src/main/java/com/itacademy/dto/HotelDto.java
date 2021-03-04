package com.itacademy.dto;

import com.itacademy.model.Country;
import com.itacademy.model.Hotel;
import com.itacademy.model.Room;

import java.util.ArrayList;
import java.util.List;

public class HotelDto {

    private String name;
    private String country;
    private Integer stars;
    private Integer rooms;

    public HotelDto(String name, String country, Integer stars, Integer rooms) {
        this.name = name;
        this.country = country;
        this.stars = stars;
        this.rooms = rooms;
    }

    public static Hotel convertToHotel(HotelDto dto){
        Hotel hotel=new Hotel();
        Country country=new Country();
        country.setName(dto.country);
        List<Room> rooms=new ArrayList<>();
        for (int i=0; i<dto.rooms;i++){
            Room room=new Room();
            room.setNumber(i);
            rooms.add(room);
        }

        hotel.setName(dto.name);
        hotel.setCountry(country);
        hotel.setStars(dto.stars);
        hotel.setRooms(rooms);

        return hotel;
    }
}
